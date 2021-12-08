package com.andon.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author Andon
 * 2021/12/7
 */
@SpringBootConfiguration
public class RabbitMQConfig {

    // work模式
    @Value("${rabbitmq.work.queue-name}")
    private String queue_name_work;
    // 订阅模式
    @Value("${rabbitmq.fanout.queue-name-1}")
    private String queue_name_fanout_1;
    @Value("${rabbitmq.fanout.queue-name-2}")
    private String queue_name_fanout_2;
    @Value("${rabbitmq.fanout.exchange-name}")
    private String exchange_name_fanout;
    // topic模式
    @Value("${rabbitmq.topic.queue-name-1}")
    private String queue_name_topic_1;
    @Value("${rabbitmq.topic.routing-key-1}")
    private String routing_key_name_topic_1;
    @Value("${rabbitmq.topic.queue-name-2}")
    private String queue_name_topic_2;
    @Value("${rabbitmq.topic.routing-key-2}")
    private String routing_key_name_topic_2;
    @Value("${rabbitmq.topic.exchange-name}")
    private String exchange_name_topic;
    // 消息confirm机制
    @Value("${rabbitmq.confirm.queue-name}")
    private String queue_name_confirm;
    // 消息return机制
    @Value("${rabbitmq.return.queue-name}")
    private String queue_name_return;
    @Value("${rabbitmq.return.routing-key}")
    private String routing_key_name_return;
    @Value("${rabbitmq.return.exchange-name}")
    private String exchange_name_return;

    /**
     * work模式
     * <p>
     * 声明一个队列名称
     */
    @Bean
    public Queue queueWork() {
        return new Queue(queue_name_work);
    }

    /**
     * 订阅模式
     * <p>
     * 声明两个队列名称
     * 声明一个交换机
     * 交换机和队列进行绑定
     */
    @Bean
    public Queue queueFanout1() {
        return new Queue(queue_name_fanout_1);
    }

    @Bean
    public Queue queueFanout2() {
        return new Queue(queue_name_fanout_2);
    }

    @Bean
    public FanoutExchange exchangeFanout() {
        return new FanoutExchange(exchange_name_fanout);
    }

    @Bean
    public Binding bindingFanoutExchange1() {
        return BindingBuilder.bind(queueFanout1()).to(exchangeFanout());
    }

    @Bean
    public Binding bindingFanoutExchange2() {
        return BindingBuilder.bind(queueFanout2()).to(exchangeFanout());
    }

    /**
     * topic模式
     * <p>
     * 声明两个队列名称
     * 声明一个交换机
     * 交换机和队列进行绑定，队列和路由键进行绑定
     */
    @Bean
    public Queue queueTopic1() {
        return new Queue(queue_name_topic_1);
    }

    @Bean
    public Queue queueTopic2() {
        return new Queue(queue_name_topic_2);
    }

    @Bean
    public TopicExchange exchangeTopic() {
        return new TopicExchange(exchange_name_topic);
    }

    @Bean
    public Binding bindingTopicExchange1() {
        return BindingBuilder.bind(queueTopic1()).to(exchangeTopic()).with(routing_key_name_topic_1);
    }

    @Bean
    public Binding bindingTopicExchange2() {
        return BindingBuilder.bind(queueTopic2()).to(exchangeTopic()).with(routing_key_name_topic_2);
    }

    /**
     * 测试消息confirm机制
     * <p>
     * 声明一个队列
     */
    @Bean
    public Queue queueConfirm() {
        return new Queue(queue_name_confirm);
    }

    /**
     * 测试消息return机制
     * <p>
     * 声明一个队列
     * 声明一个交换机
     */
    @Bean
    public Queue queueReturn() {
        return new Queue(queue_name_return);
    }

    @Bean
    public TopicExchange exchangeReturn() {
        return new TopicExchange(exchange_name_return);
    }

    @Bean
    public Binding bindingReturnExchange() {
        return BindingBuilder.bind(queueReturn()).to(exchangeReturn()).with(routing_key_name_return);
    }
}
