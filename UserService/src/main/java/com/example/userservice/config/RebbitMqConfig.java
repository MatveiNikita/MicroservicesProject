package com.example.userservice.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RebbitMqConfig {

    private final String notQueue = "notificationQueue";
    private final String notExchange = "notificationExchange";
    @Bean
    public Queue notificationQueue(){
        return new Queue(notQueue, false);
    }

    @Bean
    Exchange exchange(){
        return new TopicExchange(notExchange, false, false);
    }

    @Bean
    Binding binding(Queue queue, Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("notification.key").noargs();
    }
}
