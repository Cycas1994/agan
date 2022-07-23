package com.cycas.kafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @KafkaListener(topics = "my_first_topic")
    public void listen(ConsumerRecord<?, ?> record) {

        logger.info("topic:{}, offset:{}, value:{}", record.topic(), record.offset(), record.value());
    }
}
