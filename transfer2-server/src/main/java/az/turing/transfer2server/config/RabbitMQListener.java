package az.turing.transfer2server.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListener {

    @RabbitListener(queues = ServiceConfig.TRANSFER_NOTIFICATION_QUEUE)
    public void receiveTransferNotificationMessage(String message) {
        System.out.println("Transfer notification message: " + message);
    }
}