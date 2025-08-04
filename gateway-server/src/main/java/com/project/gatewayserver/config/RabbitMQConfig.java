package com.project.gatewayserver.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String USER_REGISTERED_QUEUE = "user-registered-queue";
    public static final String USER_EXCHANGE = "user-exchange";
    public static final String USER_LOGIN_QUEUE = "user-login-queue";
    public static final String USER_REGISTERED_ROUTING_KEY = "user.registered";
    public static final String USER_LOGIN_ROUTING_KEY = "user.login";

    @Bean
    public DirectExchange userExchange() {
        return new DirectExchange(USER_EXCHANGE);
    }

    @Bean
    public Queue userRegisteredQueue() {
        return new Queue(USER_REGISTERED_QUEUE, true);
    }

    @Bean
    public Queue userLoginQueue() {
        return new Queue(USER_LOGIN_QUEUE, true);
    }

    @Bean
    public Binding userRegisteredBinding(Queue userRegisteredQueue, DirectExchange userExchange) {
        return BindingBuilder.bind(userRegisteredQueue).to(userExchange).with(USER_REGISTERED_ROUTING_KEY);
    }

    @Bean
    public Binding userLoginBinding(Queue userLoginQueue, DirectExchange userExchange) {
        return BindingBuilder.bind(userLoginQueue).to(userExchange).with(USER_LOGIN_ROUTING_KEY);
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange(USER_EXCHANGE);
        return rabbitTemplate;
    }
}