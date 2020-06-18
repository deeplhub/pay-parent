package com.xh.pay.web.demo;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/6/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderPayTest {
    @Resource
    private JmsTemplate jmsTemplate;

    @Test
    public void pay() {
        String bankOrderNo = "100001";
        jmsTemplate.convertAndSend(new ActiveMQQueue("active.pay.queue.msg"), bankOrderNo);
    }

}
