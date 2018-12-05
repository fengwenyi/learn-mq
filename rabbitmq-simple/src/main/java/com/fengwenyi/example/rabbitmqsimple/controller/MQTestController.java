package com.fengwenyi.example.rabbitmqsimple.controller;

import com.fengwenyi.example.rabbitmqsimple.rabbitmq.MQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wenyi Feng
 * @since 2018-12-06
 */
@RestController
@RequestMapping("/mq/test")
public class MQTestController {

    @Autowired
    private MQSender mqSender;

    @RequestMapping("/send")
    public void send() {
        mqSender.send("send");
    }

    @RequestMapping("/send-topic")
    public void sendTopic() {
        mqSender.sendTopic("sendTopic");
    }

    @RequestMapping("/send-fanout")
    public void sendFanout() {
        mqSender.sendFanout("sendFanout");
    }

    @RequestMapping("/send-header")
    public void sendHeader() {
        mqSender.sendHeader("sendHeader");
    }
}
