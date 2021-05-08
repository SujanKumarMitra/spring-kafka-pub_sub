package com.github.mitrakumarsujan.kafkaproducer;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
public class KafkaController {
    private KafkaService service;

    @PostMapping("/publish")
    public CompletableFuture<String> publish(@RequestBody Message message) {
        return service.sendMessage(message)
                .thenApplyAsync(sr -> "Message published successfully")
                .exceptionally(th -> "Message could not be published");

    }
}
