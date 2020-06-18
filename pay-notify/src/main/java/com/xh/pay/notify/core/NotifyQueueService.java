package com.xh.pay.notify.core;

import com.xh.pay.SpringBootNotifyApplication;
import com.xh.pay.notify.dto.NotifyParam;
import com.xh.pay.notify.entity.NotifyRecord;
import com.xh.pay.notify.service.NotifyRecordService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/14
 */
@Component
public class NotifyQueueService {

    private static final Log LOG = LogFactory.getLog(NotifyQueueService.class);

    @Resource
    private NotifyParam notifyParam;
    @Resource
    private NotifyRecordService notifyRecordService;

    /**
     * 将传过来的对象进行通知次数判断，决定是否放在任务队列中
     *
     * @param notifyRecord
     */
    public void addToNotifyTaskDelayQueue(NotifyRecord notifyRecord) {
        if (notifyRecord == null) {
            return;
        }

        LOG.info("===> addToNotifyTaskDelayQueue notify id: " + notifyRecord.getId());
        Integer notifyTimes = notifyRecord.getNotifyTimes(); // 通知次数
        Integer maxNotifyTimes = notifyRecord.getLimitNotifyTimes(); // 最大通知次数


        if (notifyRecord.getNotifyTimes().intValue() == 0) {
            notifyRecord.setLastNotifyTime(new Date()); // 第一次发送(取当前时间)
        } else {
            notifyRecord.setLastNotifyTime(notifyRecord.getEditTime()); // 非第一次发送（取上一次修改时间，也是上一次发送时间）
        }

        if (notifyTimes < maxNotifyTimes) {
            // 未超过最大通知次数，继续下一次通知
            LOG.info("===> notify id: " + notifyRecord.getId() + ", 上次通知时间lastNotifyTime: " + notifyRecord.getLastNotifyTime());
            SpringBootNotifyApplication.tasks.put(new NotifyTask(notifyRecord, this, notifyParam));
            LOG.info("===> notify id: " + notifyRecord.getId() + " 任务添加成功后,当前队列大小: " + SpringBootNotifyApplication.tasks.size());
        }

    }
}
