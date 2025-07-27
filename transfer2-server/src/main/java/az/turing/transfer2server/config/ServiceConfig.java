package az.turing.transfer2server.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    public static final String TRANSFER_NOTIFICATION_QUEUE = "transfer-notification-queue";
    public static final String USER_EXCHANGE = "user-exchange";
    public static final String TRANSFER_NOTIFICATION_TOPIC = "transfer.notification.service";

    @Bean
    public DirectExchange userExchange() {
        return new DirectExchange(USER_EXCHANGE);
    }

    @Bean
    public Queue queue() {
        return new Queue(TRANSFER_NOTIFICATION_QUEUE);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(userExchange()).with(TRANSFER_NOTIFICATION_TOPIC);
    }
}