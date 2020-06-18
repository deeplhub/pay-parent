package com.xh.pay.notify.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xh.pay.notify.dao.rep.NotifyRecordRepDao;
import com.xh.pay.notify.entity.NotifyRecord;
import com.xh.pay.notify.service.NotifyRecordService;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/14
 */
@Service
public class NotifyRecordServiceImpl implements NotifyRecordService {
    private static final Logger LOG = LoggerFactory.getLogger(NotifyRecordServiceImpl.class);

    @Resource
    private NotifyRecordRepDao notifyRecordRepDao;
    @Resource
    private JmsTemplate jmsTemplate;

    @Override
    public List<NotifyRecord> queryNotifyRecordList() {
        return notifyRecordRepDao.queryNotifyRecordList();
    }

    @Transactional
    @Override
    public void updateNotifyRecord(NotifyRecord notifyRecord) {
        notifyRecordRepDao.save(notifyRecord);
    }

    @Transactional
    @Override
    public void saveNotifyRecord(NotifyRecord notifyRecord) {
        notifyRecordRepDao.save(notifyRecord);
    }

    @Override
    public void notifySend(String notifyUrl, String merchantOrderNo, String merchantNo) {
        NotifyRecord notifyRecord = new NotifyRecord();
        notifyRecord.setNotifyTimes(0);
        notifyRecord.setLimitNotifyTimes(5);
        notifyRecord.setStatus("通知记录已创建");
        notifyRecord.setUrl(notifyUrl);
        notifyRecord.setMerchantOrderNo(merchantOrderNo);
        notifyRecord.setMerchantNo(merchantNo);
        notifyRecord.setNotifyType("商户通知");

        Object toJSON = JSONObject.toJSON(notifyRecord);
        String str = toJSON.toString();

        LOG.info("推送到回调消息队列中");
        jmsTemplate.convertAndSend(new ActiveMQQueue("active.notify.queue.msg"), str);
    }
}
