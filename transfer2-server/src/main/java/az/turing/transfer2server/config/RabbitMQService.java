package az.turing.transfer2server.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {
    @Autowired
    private RabbitTemplate template;

    public void sendTransferNotificationMessage(String transferId) {
        String message = "{\"transferId\":\"" + transferId + "\"}";
        template.convertAndSend(ServiceConfig.USER_EXCHANGE, ServiceConfig.TRANSFER_NOTIFICATION_TOPIC, message);
    }
}