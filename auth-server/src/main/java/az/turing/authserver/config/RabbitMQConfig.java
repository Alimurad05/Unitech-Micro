package az.turing.authserver.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
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
        return new Queue(USER_REGISTERED_QUEUE);
    }

    @Bean
    public Queue userLoginQueue() {
        return new Queue(USER_LOGIN_QUEUE);
    }

    @Bean
    public Binding userRegisteredBinding() {
        return BindingBuilder.bind(userRegisteredQueue()).to(userExchange()).with(USER_REGISTERED_ROUTING_KEY);
    }

    @Bean
    public Binding userLoginBinding() {
        BindingBuilder.bind(userLoginQueue()).to(userExchange()).with(USER_LOGIN_ROUTING_KEY);
        return null;
    }
}