package com.andon.springbootrabbitmq.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Andon
 * 2021/12/7
 * <p>
 * 消费者
 */
@Slf4j
@Component
public class RabbitMQConsumer {
//
//    // work1
//    @RabbitListener(queues = "${rabbitmq.work.queue-name}")
//    public void consumeWork1(String message) {
//        log.info("consumeWork1 message:{}", message);
//    }
//
//    // work2
//    @RabbitListener(queues = "${rabbitmq.work.queue-name}")
//    public void consumeWork2(String message) {
//        log.info("consumeWork2 message:{}", message);
//    }
//
//    // 订阅1
//    @RabbitListener(queues = "${rabbitmq.fanout.queue-name-1}")
//    public void consumeFanout1(String message) {
//        log.info("consumeFanout1 message:{}", message);
//    }
//
//    // 订阅2
//    @RabbitListener(queues = "${rabbitmq.fanout.queue-name-2}")
//    public void consumeFanout2(String message) {
//        log.info("consumeFanout2 message:{}", message);
//    }
//
//    // topic1
//    @RabbitListener(queues = "${rabbitmq.topic.queue-name-1}")
//    public void consumeTopic1(String message) {
//        log.info("consumeTopic1 message:{}", message);
//    }
//
//    // topic2
//    @RabbitListener(queues = "${rabbitmq.topic.queue-name-2}")
//    public void consumeTopic2(String message) {
//        log.info("consumeTopic2 message:{}", message);
//    }
//
//    // 消息confirm机制
//    @RabbitListener(queues = "${rabbitmq.confirm.queue-name}")
//    public void consumeConfirm(String message) {
//        log.info("consumeConfirm message:{}", message);
//    }
//
//    // 消息return机制
//    @RabbitListener(queues = "${rabbitmq.return.queue-name}")
//    public void consumeReturn(String message) {
//        log.info("consumeReturn message:{}", message);
//    }
}
