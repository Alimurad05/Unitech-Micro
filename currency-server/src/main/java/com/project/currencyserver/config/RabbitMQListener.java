package com.project.currencyserver.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListener {

    @RabbitListener(queues = RabbitMQConfig.CURRENCY_UPDATE_QUEUE)
    public void receiveCurrencyUpdateMessage(String message) {
        System.out.println("Currency update message: " + message);
    }
}