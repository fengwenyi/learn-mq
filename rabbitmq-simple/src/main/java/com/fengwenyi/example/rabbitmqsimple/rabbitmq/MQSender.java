package com.fengwenyi.example.rabbitmqsimple.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MQ 发送端
 * @author Wenyi Feng
 * @since 2018-12-06
 */
@Service
@Slf4j
public class MQSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(Object message) {
        amqpTemplate.convertAndSend(message);
        log.info("send message => [{}]", message);
    }

    public void sendTopic(Object message) {
        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, "topic.key1", message);
        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, "topic.key2", message);
        log.info("send topic message => [{}]", message);
    }

    public void sendFanout(Object message) {
        amqpTemplate.convertAndSend(MQConfig.FANOUT_EXCHANGE, "",message);
        log.info("send fanout message => [{}]", message);
    }

    public void sendHeader(Object message) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("header1", "value1");
        messageProperties.setHeader("header2", "value2");
        Message obj = new Message(message.toString().getBytes(), messageProperties);
        amqpTemplate.convertAndSend(MQConfig.HEADERS_EXCHANGE, "", obj);
        log.info("send message => [{}]", message);
    }

}
