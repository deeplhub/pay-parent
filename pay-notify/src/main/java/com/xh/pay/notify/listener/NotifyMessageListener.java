package com.xh.pay.notify.listener;

import com.alibaba.fastjson.JSON;
import com.xh.pay.notify.config.Cons;
import com.xh.pay.notify.core.NotifyQueueService;
import com.xh.pay.notify.dto.NotifyParam;
import com.xh.pay.notify.entity.NotifyRecord;
import com.xh.pay.notify.service.NotifyRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Title: 回调监听
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/14
 */
@Component
public class NotifyMessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(NotifyMessageListener.class);

    @Resource
    private NotifyQueueService notifyQueueService;
    @Resource
    private NotifyRecordService notifyRecordService;
    @Resource
    private NotifyParam notifyParam;

    @JmsListener(destination = "active.notify.queue.msg", containerFactory = Cons.QUEUE_LISTENER)
    public void onMessage(String message) throws InterruptedException {
        LOG.info("== receive message: " + message);
        try {
            NotifyRecord notifyRecord = JSON.parseObject(message, NotifyRecord.class);
            if (notifyRecord == null) {
                return;
            }

            notifyRecord.setStatus("通知记录已创建");
            notifyRecord.setCreateTime(new Date());
            notifyRecord.setEditTime(new Date());
            notifyRecord.setLastNotifyTime(new Date());
            notifyRecord.setNotifyTimes(0); // 初始化通知0次
            notifyRecord.setLimitNotifyTimes(notifyParam.getMaxNotifyTime()); // 最大通知次数

            try {
                notifyRecordService.saveNotifyRecord(notifyRecord);// 将获取到的通知先保存到数据库中
                notifyQueueService.addToNotifyTaskDelayQueue(notifyRecord);// 添加到通知队列(第一次通知)
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
