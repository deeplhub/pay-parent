package com.xh.pay.polling.core;

import com.xh.pay.SpringBootPollingApplication;
import com.xh.pay.polling.dto.OrderResultQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Title: 轮询任务
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/13
 */
public class PollingTask implements Runnable, Delayed {

    private static final Logger LOG = LoggerFactory.getLogger(PollingTask.class);

    private OrderResultQuery orderResultQuery;

    private PollingPersistService pollingPersistService = SpringBootPollingApplication.pollingPersist;

    private long executeTime;

    public PollingTask() {
    }

    public PollingTask(OrderResultQuery orderResultQuery) {
        super();
        this.orderResultQuery = orderResultQuery;
        this.executeTime = this.getExecuteTime(orderResultQuery);
    }

    /**
     * 计算任务允许执行的开始时间(executeTime)
     *
     * @param orderResultQuery
     * @return
     */
    private long getExecuteTime(OrderResultQuery orderResultQuery) {
        long lastNotifyTime = orderResultQuery.getLastNotifyTime().getTime(); // 最后通知时间（上次通知时间）
        Integer notifyTimes = orderResultQuery.getNotifyTimes(); // 已通知次数
        LOG.info("===> pollingTimes: {}", notifyTimes);
        Integer nextNotifyTimeInterval = orderResultQuery.getNotifyRuleMap().get(notifyTimes + 1); // 当前发送次数对应的时间间隔数（分钟数）
        long nextNotifyTime = (nextNotifyTimeInterval == null ? 0 : nextNotifyTimeInterval * 1000) + lastNotifyTime;
        LOG.info("===> notify id:{}, nextNotifyTime: {}", orderResultQuery.getId(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date(nextNotifyTime)));
        return nextNotifyTime;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }

    /**
     * 执行通知处理
     */
    @Override
    public void run() {
        // 获取订单结果
        pollingPersistService.getOrderResult(orderResultQuery);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }
}
