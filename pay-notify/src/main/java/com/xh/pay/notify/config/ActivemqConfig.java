package com.xh.pay.notify.config;

import org.messaginghub.pooled.jms.JmsPoolConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;

/**
 * Title: MQ配置 - 消费者
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/9
 */
@Configuration
public class ActivemqConfig {

    /**
     * 在Queue模式中，对消息的监听需要对containerFactory进行配置
     *
     * @param poolConnectionFactory
     * @return
     */
    @Bean(Cons.QUEUE_LISTENER)
    public JmsListenerContainerFactory<?> queueJmsListenerContainerFactory(JmsPoolConnectionFactory poolConnectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(poolConnectionFactory);
        factory.setPubSubDomain(false);//监听的是Queue
        return factory;
    }

    /**
     * 在Topic模式中，对消息的监听需要对containerFactory进行配置
     *
     * @param poolConnectionFactory
     * @return
     */
    @Bean(Cons.TOPIC_LISTENER)
    public JmsListenerContainerFactory<?> topicJmsListenerContainerFactory(JmsPoolConnectionFactory poolConnectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(poolConnectionFactory);
        factory.setPubSubDomain(true);//监听的是Topic
        return factory;
    }
}
