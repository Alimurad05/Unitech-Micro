package com.project.currencyserver.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendCurrencyUpdateMessage(String currencyCode, String rate) {
        String message = "{\"currencyCode\":\"" + currencyCode + "\",\"rate\":\"" + rate + "\"}";
        rabbitTemplate.convertAndSend(RabbitMQConfig.USER_EXCHANGE, RabbitMQConfig.CURRENCY_UPDATE_ROUTING_KEY, message);
    }
}