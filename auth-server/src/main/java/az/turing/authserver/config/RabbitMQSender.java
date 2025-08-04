package az.turing.authserver.config;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendUserRegisteredMessage(String email, String token) {
        String message = "{\"email\":\"" + email + "\",\"token\":\"" + token + "\"}";
        rabbitTemplate.convertAndSend(RabbitMQConfig.USER_EXCHANGE, RabbitMQConfig.USER_REGISTERED_ROUTING_KEY, message);
    }

    public void sendUserLoginMessage(String email, String token) {
        String message = "{\"email\":\"" + email + "\",\"token\":\"" + token + "\"}";
        rabbitTemplate.convertAndSend(RabbitMQConfig.USER_EXCHANGE, RabbitMQConfig.USER_LOGIN_ROUTING_KEY, message);
    }
}
