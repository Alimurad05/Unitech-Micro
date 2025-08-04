package project.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String ACCOUNT_UPDATE_QUEUE = "account-update-queue";
    public static final String USER_EXCHANGE = "user-exchange";
    public static final String ACCOUNT_UPDATE_ROUTING_KEY = "account.update";

    @Bean
    public DirectExchange userExchange() {
        return new DirectExchange(USER_EXCHANGE);
    }

    @Bean
    public Queue accountUpdateQueue() {
        return new Queue(ACCOUNT_UPDATE_QUEUE);
    }

    @Bean
    public Binding accountUpdateBinding() {
        return BindingBuilder.bind(accountUpdateQueue()).to(userExchange()).with(ACCOUNT_UPDATE_ROUTING_KEY);
    }
}