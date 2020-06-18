package com.xh.pay.notify.service;

import com.xh.pay.notify.entity.NotifyRecord;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/14
 */
public interface NotifyRecordLogService {

    void saveNotifyRecordLogs(NotifyRecord notifyRecord, String response, int httpStatus);

}
