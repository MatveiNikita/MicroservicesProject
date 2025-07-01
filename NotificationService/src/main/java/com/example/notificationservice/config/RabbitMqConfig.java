package com.example.notificationservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    private final String notQueue = "notificationQueue";

    @Bean
    public Queue notificationQueue(){
        return new Queue(notQueue, false);
    }
}
