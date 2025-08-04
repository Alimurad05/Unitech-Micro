package project.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListener {

    @RabbitListener(queues = RabbitMQConfig.ACCOUNT_UPDATE_QUEUE)
    public void receiveAccountUpdateMessage(String message) {
        System.out.println("Account update message: " + message);
    }
}