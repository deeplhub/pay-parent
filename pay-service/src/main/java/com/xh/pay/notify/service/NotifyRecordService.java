package com.xh.pay.notify.service;


import com.xh.pay.notify.entity.NotifyRecord;

import java.util.List;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/14
 */
public interface NotifyRecordService {

    List<NotifyRecord> queryNotifyRecordList();

    void updateNotifyRecord(NotifyRecord notifyRecord);

    void saveNotifyRecord(NotifyRecord notifyRecord);

    /**
     * 发送消息通知
     * @param notifyUrl 通知地址
     * @param merchantOrderNo 商户订单号
     * @param merchantNo 商户号
     */
    void notifySend(String notifyUrl, String merchantOrderNo, String merchantNo);
}
