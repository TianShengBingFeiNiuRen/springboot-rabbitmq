package com.andon.springbootrabbitmq;

import com.andon.springbootrabbitmq.rabbitmq.RabbitMQProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Andon
 * 2021/12/7
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseTest {

    @Resource
    private RabbitMQProducer rabbitMQProducer;

    @Test
    public void test05() {
        rabbitMQProducer.sendReturn("return.an.return", "return!!");
    }

    @Test
    public void test04() {
        rabbitMQProducer.sendConfirm("confirm!!");
    }

    @Test
    public void test03() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                rabbitMQProducer.sendTopic("topic.an.topic", "topic" + i);
            } else {
                rabbitMQProducer.sendTopic("topic.an", "topic" + i);
            }
        }
    }

    @Test
    public void test02() {
        for (int i = 0; i < 10; i++) {
            rabbitMQProducer.sendFanout("fanout" + i);
        }
    }

    @Test
    public void test01() {
        for (int i = 0; i < 10; i++) {
            rabbitMQProducer.sendWork("work" + i);
        }
    }
}
