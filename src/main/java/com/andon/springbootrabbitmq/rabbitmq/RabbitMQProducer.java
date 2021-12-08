package com.andon.springbootrabbitmq.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Andon
 * 2021/12/7
 * <p>
 * 生产者
 */
@Slf4j
@Component
public class RabbitMQProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;
    // work模式
    @Value("${rabbitmq.work.queue-name}")
    private String queue_name_work;
    // 订阅模式
    @Value("${rabbitmq.fanout.exchange-name}")
    private String exchange_name_fanout;
    // topic模式
    @Value("${rabbitmq.topic.exchange-name}")
    private String exchange_name_topic;
    // 消息confirm机制
    @Value("${rabbitmq.confirm.queue-name}")
    private String queue_name_confirm;
    // 消息return机制
    @Value("${rabbitmq.return.exchange-name}")
    private String exchange_name_return;

    /**
     * work模式
     * <p>
     * 消息直接发送到消息队列
     * 一个生产者，多个消费者，每个消息只能被一个消费者消费
     */
    public void sendWork(String message) {
        rabbitTemplate.convertAndSend(queue_name_work, message);
    }

    /**
     * 订阅模式
     * <p>
     * 消息发送到交换机
     * 一个生产者，多个消费者，每个消息被每个消费者消费
     */
    public void sendFanout(String message) {
        rabbitTemplate.convertAndSend(exchange_name_fanout, "", message);
    }

    /**
     * topic模式
     * <p>
     * 消息发送到交换机
     * 一个生产者，多个消费者，每个消息被每个对应路由键的消费者消费
     */
    public void sendTopic(String routingKey, String message) {
        rabbitTemplate.convertAndSend(exchange_name_topic, routingKey, message);
    }

    /**
     * 消息confirm机制
     */
    public void sendConfirm(Object message) {
        CorrelationData correlationData = new CorrelationData(Thread.currentThread().getId() + "_" + System.currentTimeMillis());
        rabbitTemplate.convertAndSend(queue_name_confirm, message, correlationData);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (ack) {
                    log.info("消息confirm成功!! id:{}", correlationData.getId());
                } else {
                    log.info("消息confirm失败!! id:{} cause:{}", correlationData.getId(), cause);
                }
            }
        });
    }

    /**
     * 消息return机制
     * <p>
     * return 的回调方法（找不到路由才会触发）
     */
    public void sendReturn(String routingKey, String message) {
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returned) {
                log.info("returnedMessage >> exchange:{} routingKey:{} getMessage:{} replyCode:{} replyText:{}", returned.getExchange(), returned.getRoutingKey(), returned.getMessage(), returned.getReplyCode(), returned.getReplyText());
            }
        });
        rabbitTemplate.convertAndSend(exchange_name_return, routingKey, message);
    }
}
