package com.project.currencyserver.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String CURRENCY_UPDATE_QUEUE = "currency-update-queue";
    public static final String USER_EXCHANGE = "user-exchange";
    public static final String CURRENCY_UPDATE_ROUTING_KEY = "currency.update";

    @Bean
    public DirectExchange userExchange() {
        return new DirectExchange(USER_EXCHANGE);
    }

    @Bean
    public Queue currencyUpdateQueue() {
        return new Queue(CURRENCY_UPDATE_QUEUE);
    }

    @Bean
    public Binding currencyUpdateBinding() {
        return BindingBuilder.bind(currencyUpdateQueue()).to(userExchange()).with(CURRENCY_UPDATE_ROUTING_KEY);
    }
}