package com.xh.pay.polling.core;

import com.xh.pay.SpringBootPollingApplication;
import com.xh.pay.polling.dto.OrderResultQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Title: 轮询消息通知
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/15
 */
@Component
public class PollingQueueService {

    private static final Logger LOG = LoggerFactory.getLogger(PollingQueueService.class);

    /**
     * 将传过来的对象进行通知次数判断，决定是否放在任务队列中
     * @param orderResultQuery
     */
    public void addToNotifyTaskDelayQueue(OrderResultQuery orderResultQuery) {
        if (orderResultQuery == null) {
            return;
        }
        LOG.info("===> addToOrderQueryTaskDelayQueue bank order no: " + orderResultQuery.getBankOrderNo());
        Integer notifyTimes = orderResultQuery.getNotifyTimes(); // 通知次数
        Integer maxNotifyTimes = orderResultQuery.getLimitNotifyTimes(); // 最大通知次数

        if (orderResultQuery.getNotifyTimes().intValue() == 0) {
            orderResultQuery.setLastNotifyTime(new Date()); // 第一次发送(取当前时间)
        } else {
            orderResultQuery.setLastNotifyTime(orderResultQuery.getEditTime()); // 非第一次发送（取上一次修改时间，也是上一次发送时间）
        }

        if (notifyTimes < maxNotifyTimes) {
            // 未超过最大通知次数，继续下一次通知
            LOG.info("===> bank order No  " + orderResultQuery.getBankOrderNo() + ", 上次通知时间lastNotifyTime: "
                    + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(orderResultQuery.getLastNotifyTime()));

            SpringBootPollingApplication.tasks.put(new PollingTask(orderResultQuery));
        }
    }
}
