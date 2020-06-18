package com.xh.pay.polling.core;

import com.xh.pay.polling.dto.OrderResultQuery;
import com.xh.pay.polling.service.TradePaymentRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Title: 轮询订单结果
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/13
 */
@Component
public class PollingPersistService {

    private static final Logger LOG = LoggerFactory.getLogger(PollingPersistService.class);

    @Resource
    private TradePaymentRecordService tradePaymentRecordService;
    @Resource
    private PollingQueueService pollingQueueService;

    /**
     * 获取订单结果
     *
     * @param orderResultQuery
     */
    public void getOrderResult(OrderResultQuery orderResultQuery) {
        Integer notifyTimes = orderResultQuery.getNotifyTimes(); // 得到当前通知对象的通知次数
        Integer maxNotifyTimes = orderResultQuery.getLimitNotifyTimes(); // 最大通知次数
        Date notifyTime = new Date(); // 本次通知的时间

        try {
            // 处理交易记录
            boolean processingResult = tradePaymentRecordService.processingTradeRecord(orderResultQuery.getBankOrderNo());
            LOG.info("order processing result: {}", processingResult);

            orderResultQuery.setEditTime(notifyTime); // 取本次通知时间作为最后修改时间
            orderResultQuery.setNotifyTimes(notifyTimes + 1); // 通知次数+1
            LOG.info("notifyTimes: {}  , maxNotifyTimes: {} ", notifyTimes, maxNotifyTimes);


            // 返回失败,说明还未支付
            if (!processingResult) {
                // 通知不成功（返回的结果不是success）
                if (orderResultQuery.getNotifyTimes() < maxNotifyTimes) {
                    // 判断是否超过重发次数，未超重发次数的，再次进入延迟发送队列
                    pollingQueueService.addToNotifyTaskDelayQueue(orderResultQuery);
                    LOG.info("===>bank order {} need processing again ", orderResultQuery.getBankOrderNo());
                } else {
                    LOG.info("bank order No {} not pay", orderResultQuery.getBankOrderNo());
                }
            }

        } catch (Exception e) {
            LOG.error("订单处理系统异常:", e);
            if (orderResultQuery.getNotifyTimes() < maxNotifyTimes) {
                // 判断是否超过重发次数，未超重发次数的，再次进入延迟发送队列
                pollingQueueService.addToNotifyTaskDelayQueue(orderResultQuery);
                LOG.info("===>bank order {} need processing again ", orderResultQuery.getBankOrderNo());
            } else {
                LOG.info("bank order No {} not pay", orderResultQuery.getBankOrderNo());
            }
        }
    }
}
