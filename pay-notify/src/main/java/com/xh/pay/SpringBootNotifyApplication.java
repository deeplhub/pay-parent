package com.xh.pay;

import com.xh.pay.notify.core.NotifyQueueService;
import com.xh.pay.notify.core.NotifyTask;
import com.xh.pay.notify.entity.NotifyRecord;
import com.xh.pay.notify.service.NotifyRecordLogService;
import com.xh.pay.notify.service.NotifyRecordService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.concurrent.DelayQueue;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/17
 */
@SpringBootApplication
public class SpringBootNotifyApplication {
    private static final Log LOG = LogFactory.getLog(SpringBootNotifyApplication.class);
    public static DelayQueue<NotifyTask> tasks = new DelayQueue<NotifyTask>();
    public static NotifyRecordService notifyRecord;
    public static NotifyRecordLogService notifyRecordLog;

    @Autowired
    private ThreadPoolTaskExecutor threadPool;
    @Autowired
    public NotifyRecordService notifyRecordService;
    @Autowired
    public NotifyRecordLogService notifyRecordLogService;
    @Autowired
    private NotifyQueueService notifyQueueService;

    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(SpringBootNotifyApplication.class).web(WebApplicationType.NONE).run(args);
    }

    @PostConstruct
    public void init() {
        notifyRecord = notifyRecordService;
        notifyRecordLog = notifyRecordLogService;
        startInitFromDB();
        startThread();
    }

    /**
     * 从数据库中取一次数据用来当系统启动时初始化
     */
    private void startInitFromDB() {
        LOG.info("get data from database");

        List<NotifyRecord> list = notifyRecordService.queryNotifyRecordList();
        for (int i = 0; i < list.size(); i++) {
            NotifyRecord notifyRecord = list.get(i);
            notifyRecord.setLastNotifyTime(new Date());
            notifyQueueService.addToNotifyTaskDelayQueue(notifyRecord);
        }
    }

    private void startThread() {
        LOG.info("startThread");

        threadPool.execute(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(50);//50毫秒执行一次
                        // 如果当前活动线程等于最大线程，那么不执行
                        if (threadPool.getActiveCount() < threadPool.getMaxPoolSize()) {
                            final NotifyTask task = tasks.poll();
                            if (task != null) {
                                threadPool.execute(new Runnable() {
                                    public void run() {
                                        LOG.info(threadPool.getActiveCount() + "---------");
                                        tasks.remove(task);
                                        task.run();
                                    }
                                });
                            }
                        }
                    }
                } catch (Exception e) {
                    LOG.error("系统异常", e);
                    e.printStackTrace();
                }
            }
        });
    }

}
