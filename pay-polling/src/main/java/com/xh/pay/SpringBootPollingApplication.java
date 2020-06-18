package com.xh.pay;

import com.xh.pay.polling.core.PollingPersistService;
import com.xh.pay.polling.core.PollingTask;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.DelayQueue;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/15
 */
@SpringBootApplication
public class SpringBootPollingApplication {

    private static final Log LOG = LogFactory.getLog(SpringBootPollingApplication.class);

    public static PollingPersistService pollingPersist;
    public static DelayQueue<PollingTask> tasks = new DelayQueue<PollingTask>();

    @Resource
    private PollingPersistService pollingPersistService;
    @Autowired
    private ThreadPoolTaskExecutor threadPool;// 线程池


    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(SpringBootPollingApplication.class).web(WebApplicationType.NONE).run(args);
    }

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        pollingPersist = pollingPersistService;
        startThread();
    }

    private void startThread() {
        LOG.info("==>startThread");
        threadPool.execute(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(100);
                        LOG.info("==>threadPool.getActiveCount():" + threadPool.getActiveCount());
                        LOG.info("==>threadPool.getMaxPoolSize():" + threadPool.getMaxPoolSize());
                        // 如果当前活动线程等于最大线程，那么不执行
                        if (threadPool.getActiveCount() < threadPool.getMaxPoolSize()) {
                            LOG.info("==>tasks.size():" + tasks.size());
                            final PollingTask task = tasks.take(); //使用take方法获取过期任务,如果获取不到,就一直等待,知道获取到数据
                            if (task != null) {
                                threadPool.execute(new Runnable() {
                                    public void run() {
                                        tasks.remove(task);
                                        task.run(); // 执行通知处理
                                        LOG.info("==>tasks.size():" + tasks.size());
                                    }
                                });
                            }
                        }
                    }
                } catch (Exception e) {
                    LOG.error("系统异常;", e);
                }
            }
        });
    }

}
