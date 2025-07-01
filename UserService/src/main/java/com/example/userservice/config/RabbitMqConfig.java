package com.example.userservice.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfig {

    private final String notificationQueue = "notificationQueue";
    private final String notificationExchange = "notificationExchange";
    private final String routingKey = "notification.key";

    @Bean
    public Queue notificationQueue(){
        return new Queue(notificationQueue, false);
    }

    @Bean
    Exchange exchange(){
        return new TopicExchange(notificationExchange, false, false);
    }

    @Bean
    Binding binding(Queue queue, Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(routingKey).noargs();
    }
}
