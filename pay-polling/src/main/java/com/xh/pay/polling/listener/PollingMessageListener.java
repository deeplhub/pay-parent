package com.xh.pay.polling.listener;

import com.alibaba.fastjson.JSONObject;
import com.xh.pay.common.core.constant.Cons;
import com.xh.pay.polling.core.PollingQueueService;
import com.xh.pay.polling.dto.OrderResultQuery;
import com.xh.pay.polling.dto.PollingParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/13
 */
@Component
public class PollingMessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(PollingMessageListener.class);

    @Autowired
    private PollingQueueService pollingQueueService;
    @Autowired
    private PollingParam pollingParam;

    @JmsListener(destination = "active.pay.queue.msg", containerFactory = Cons.QUEUE_LISTENER)
    public void onMessage(String message) {
        LOG.info("message = ", message);
        OrderResultQuery order = new OrderResultQuery();
        order.setBankOrderNo(message);
        order.setStatus("通知记录已创建");
        order.setCreateTime(new Date());
        order.setEditTime(new Date());
        order.setLastNotifyTime(new Date());
        order.setNotifyTimes(0); // 初始化通知0次
        order.setLimitNotifyTimes(pollingParam.getMaxNotifyTimes()); // 最大通知次数
        Map<Integer, Integer> notifyParams = pollingParam.getNotifyParams();
        order.setNotifyRule(JSONObject.toJSONString(notifyParams)); // 保存JSON

        try {
            pollingQueueService.addToNotifyTaskDelayQueue(order); // 添加到通知队列(第一次通知)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
