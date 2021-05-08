package com.github.mitrakumarsujan.kafkaconsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class KafkaConsumer {

    private ObjectMapper mapper;

    @KafkaListener(topics = "messages")
    public void consume(String msg) throws JsonProcessingException {
        Message message = mapper.readValue(msg, Message.class);
        log.info("Message Received {}", message);
    }
}
