package com.cycas.kafka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @GetMapping("/send")
    public String send(@RequestParam("topic") String topic, @RequestParam("msg") String msg) {

        kafkaTemplate.send(topic, msg);
        logger.info("发送消息的topic：{}，发送的内容：{}", topic, msg);
        return "success";
    }
}
