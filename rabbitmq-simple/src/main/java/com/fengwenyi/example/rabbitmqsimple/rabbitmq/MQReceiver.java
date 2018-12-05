package com.fengwenyi.example.rabbitmqsimple.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * MQ 接收端
 * @author Wenyi Feng
 * @since 2018-12-06
 */
@Service
@Slf4j
public class MQReceiver {

    @RabbitListener(queues = MQConfig.QUEUE)
    public void receive(String message) {
        log.info("receive message => [{}]", message);
    }

    @RabbitListener(queues = MQConfig.QUEUE_TOPIC_1)
    public void receiveTopic1(String message) {
        log.info("receive topic1 message => [{}]", message);
    }

    @RabbitListener(queues = MQConfig.QUEUE_TOPIC_2)
    public void receiveTopic2(String message) {
        log.info("receive topic2 message => [{}]", message);
    }

    @RabbitListener(queues = MQConfig.QUEUE_HEADER)
    public void receiveHeader(String message) {
        log.info("receive header message => [{}]", message);
    }

}
