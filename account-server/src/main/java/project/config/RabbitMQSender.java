package project.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendAccountUpdateMessage(String accountId) {
        String message = "{\"accountId\":\"" + accountId + "\"}";
        rabbitTemplate.convertAndSend(RabbitMQConfig.USER_EXCHANGE, RabbitMQConfig.ACCOUNT_UPDATE_ROUTING_KEY, message);
    }
}