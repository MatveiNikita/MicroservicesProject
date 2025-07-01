package com.example.notificationservice.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    @RabbitListener(queues = "notificationQueue")
    public void handleNotification(String message) {
        System.out.println("📩 Received notification: " + message);
    }
}
