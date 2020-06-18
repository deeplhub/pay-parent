package com.xh.pay.notify.core;

import com.xh.pay.SpringBootNotifyApplication;
import com.xh.pay.notify.dto.HttpResult;
import com.xh.pay.notify.dto.NotifyParam;
import com.xh.pay.notify.entity.NotifyRecord;
import com.xh.pay.notify.service.NotifyRecordLogService;
import com.xh.pay.notify.service.NotifyRecordService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/14
 */
public class NotifyTask implements Runnable, Delayed {

    private static final Log LOG = LogFactory.getLog(NotifyTask.class);

    private long executeTime;
    private NotifyRecord notifyRecord;
    private NotifyQueueService notifyQueueService;
    private NotifyParam notifyParam;
    private NotifyRecordService notifyRecordService = SpringBootNotifyApplication.notifyRecord;
    private NotifyRecordLogService notifyRecordLogService = SpringBootNotifyApplication.notifyRecordLog;


    public NotifyTask() {
    }

    public NotifyTask(NotifyRecord notifyRecord, NotifyQueueService notifyQueueService, NotifyParam notifyParam) {
        super();
        this.notifyRecord = notifyRecord;
        this.notifyQueueService = notifyQueueService;
        this.notifyParam = notifyParam;
        this.executeTime = getExecuteTime(notifyRecord);
    }

    /**
     * 计算任务允许执行的开始时间(executeTime)
     *
     * @param notifyRecord
     * @return
     */
    private long getExecuteTime(NotifyRecord notifyRecord) {
        long lastNotifyTime = notifyRecord.getLastNotifyTime().getTime();// 最后通知时间（上次通知时间）
        Integer notifyTimes = notifyRecord.getNotifyTimes(); // 已通知次数
        LOG.info("===>notifyTimes:" + notifyTimes);

        // 当前发送次数对应的时间间隔数（分钟数）
        Integer nextNotifyTimeInterval = notifyParam.getNotifyParams().get(notifyTimes + 1); // 当前发送次数对应的时间间隔数（分钟数）
        long nextNotifyTime = (nextNotifyTimeInterval == null ? 0 : nextNotifyTimeInterval * 60 * 1000) + lastNotifyTime;
        LOG.info("===>notify id:" + notifyRecord.getId() + ", nextNotifyTime:" + new Date(nextNotifyTime));
        return nextNotifyTime;

    }


    @Override
    public int compareTo(Delayed o) {
        return 0;
    }

    /**
     * 执行通知处理.
     */
    @Override
    public void run() {
        LOG.info("开始任务执行: ==>");
        Integer notifyTimes = notifyRecord.getNotifyTimes();// 得到当前通知对象的通知次数
        Integer maxNotifyTimes = notifyRecord.getLimitNotifyTimes(); // 最大通知次数

        // 去通知
        try {
            LOG.info("Notify Url " + notifyRecord.getUrl() + "; notify id:" + notifyRecord.getId() + "; notify times:" + notifyRecord.getNotifyTimes());

            //模拟请求支付宝回调响应
            HttpResult result = new HttpResult();

            notifyRecord.setNotifyTimes(notifyTimes + 1);
            String successValue = notifyParam.getSuccessValue(); // 通知成功标识

            String responseMsg = "";
            Integer responseStatus = result.getStatusCode();

            // 写通知日志表
            notifyRecordLogService.saveNotifyRecordLogs(notifyRecord, responseMsg, responseStatus);
            LOG.info("Insert NotifyRecordLog, merchantNo: " + notifyRecord.getMerchantNo() + ", merchantOrderNo:" + notifyRecord.getMerchantOrderNo());


            // 得到返回状态，如果是200，也就是通知成功
            if ((responseStatus == 200 || responseStatus == 201 || responseStatus == 202 || responseStatus == 203 || responseStatus == 204 || responseStatus == 205 || responseStatus == 206)) {
                responseMsg = "success";
                responseMsg = responseMsg.length() >= 600 ? responseMsg.substring(0, 600) : responseMsg;
                LOG.info("订单号： " + notifyRecord.getMerchantOrderNo() + "， HTTP_STATUS： " + responseStatus + "， 请求返回信息： " + responseMsg);
                // 通知成功,更新通知记录为已通知成功（以后不再通知）
                if (responseMsg.trim().equals(successValue)) {
                    notifyRecord.setStatus("通知成功");
                    notifyRecord.setLastNotifyTime(new Date());// 本次通知的时间
                    notifyRecordService.updateNotifyRecord(notifyRecord);
                    return;
                }

                // 通知不成功（返回的结果不是success）
                if (notifyRecord.getNotifyTimes() < maxNotifyTimes) {
                    // 判断是否超过重发次数，未超重发次数的，再次进入延迟发送队列
                    notifyQueueService.addToNotifyTaskDelayQueue(notifyRecord);
                    notifyRecord.setStatus("http请求响应成功");
                    notifyRecord.setLastNotifyTime(new Date());// 本次通知的时间
                    notifyRecordService.updateNotifyRecord(notifyRecord);
                    LOG.info("===> update NotifyRecord status to HTTP_REQUEST_SUCCESS, notifyId: " + notifyRecord.getId());
                } else {
                    // 到达最大通知次数限制，标记为通知失败
                    notifyRecord.setStatus("通知失败");
                    notifyRecord.setLastNotifyTime(new Date());// 本次通知的时间
                    notifyRecordService.updateNotifyRecord(notifyRecord);
                    LOG.info("===> update NotifyRecord status to failed, notifyId: " + notifyRecord.getId());
                }

            } else {
                // 其它HTTP响应状态码情况下
                if (notifyRecord.getNotifyTimes() < maxNotifyTimes) {
                    // 判断是否超过重发次数，未超重发次数的，再次进入延迟发送队列
                    notifyQueueService.addToNotifyTaskDelayQueue(notifyRecord);

                    notifyRecord.setStatus("http请求失败");
                    notifyRecord.setLastNotifyTime(new Date());// 本次通知的时间
                    notifyRecordService.updateNotifyRecord(notifyRecord);
                    LOG.info("===> update NotifyRecord status to HTTP_REQUEST_FALIED, notifyId: " + notifyRecord.getId());
                } else {
                    // 到达最大通知次数限制，标记为通知失败
                    notifyRecord.setStatus("通知失败");
                    notifyRecord.setLastNotifyTime(new Date());// 本次通知的时间
                    notifyRecordService.updateNotifyRecord(notifyRecord);
                    LOG.info("===> update NotifyRecord status to failed, notifyId: " + notifyRecord.getId());
                }

            }


        } catch (Exception e) {
            LOG.error("NotifyTask", e);

            notifyRecord.setEditTime(new Date()); // 取本次通知时间作为最后修改时间
            notifyRecord.setNotifyTimes(notifyTimes + 1); // 通知次数+1

            if (notifyRecord.getNotifyTimes() < maxNotifyTimes) {
                // 判断是否超过重发次数，未超重发次数的，再次进入延迟发送队列
                notifyQueueService.addToNotifyTaskDelayQueue(notifyRecord);

                notifyRecord.setStatus("http请求失败");
                notifyRecord.setLastNotifyTime(new Date());// 本次通知的时间
                notifyRecordService.updateNotifyRecord(notifyRecord);
                LOG.info("===> update NotifyRecord status to HTTP_REQUEST_FALIED, notifyId: " + notifyRecord.getId());
            } else {
                // 到达最大通知次数限制，标记为通知失败
                notifyRecord.setStatus("通知失败");
                notifyRecord.setLastNotifyTime(new Date());// 本次通知的时间
                notifyRecordService.updateNotifyRecord(notifyRecord);
                LOG.info("===> update NotifyRecord status to failed, notifyId: " + notifyRecord.getId());
            }

            LOG.error("===>PollingTask", e);
            notifyRecordLogService.saveNotifyRecordLogs(notifyRecord, "", 0);
        }


    }

    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }
}
