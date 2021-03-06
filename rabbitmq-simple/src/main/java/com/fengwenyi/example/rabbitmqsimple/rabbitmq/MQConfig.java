package com.fengwenyi.example.rabbitmqsimple.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * MQ配置
 * @author Wenyi Feng
 * @since 2018-12-06
 */
@Configuration
public class MQConfig {

    public static final String QUEUE = "queue";
    public static final String QUEUE_TOPIC_1 = "queue.topic.1";
    public static final String QUEUE_TOPIC_2 = "queue.topic.2";
    public static final String QUEUE_HEADER = "queue.header";
    public static final String TOPIC_EXCHANGE = "topic.exchange";
    public static final String FANOUT_EXCHANGE = "fanout.exchange";
    public static final String HEADERS_EXCHANGE = "headers.exchange";

    /**
     * Direct模式，交换机Exchange
     * @return
     */
    @Bean
    public Queue queue() {
        return new Queue(QUEUE, true);
    }

    /**
     * Topic模式，交换机Exchange
     * @return
     */
    @Bean
    public Queue topicQueue1() {
        return new Queue(QUEUE_TOPIC_1, true);
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue(QUEUE_TOPIC_2, true);
    }


    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("topic.key1");
    }

    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("topic.#");
    }

    /**
     * Fanout模式，交换机Exchange
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Binding FanoutBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding FanoutBinding2() {
        return BindingBuilder.bind(topicQueue2()).to(fanoutExchange());
    }

    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange(HEADERS_EXCHANGE);
    }

    @Bean
    public Queue headerQueue1() {
        return new Queue(QUEUE_HEADER, true);
    }

    @Bean
    public Binding headerBinding() {
        Map<String, Object> map = new HashMap<>();
        map.put("header1", "value1");
        map.put("header2", "value2");
        return BindingBuilder.bind(headerQueue1()).to(headersExchange()).whereAll(map).match();
    }
}
