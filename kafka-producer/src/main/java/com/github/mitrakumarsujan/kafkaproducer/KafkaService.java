package com.github.mitrakumarsujan.kafkaproducer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class KafkaService {

    private KafkaTemplate<String,String> kafkaTemplate;
    private ObjectMapper mapper;

    public CompletableFuture<SendResult<String, String>> sendMessage(Message message) {
        try {
            String json = mapper.writeValueAsString(message);
            return kafkaTemplate.send("messages", json).completable();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
