package az.turing.authserver.service;

import az.turing.authserver.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendUserRegisteredEvent(String email) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.USER_REGISTERED_QUEUE, email);
    }
    public void sendUserLoginEvent(String email) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.USER_LOGIN_QUEUE, email);
    }
}
