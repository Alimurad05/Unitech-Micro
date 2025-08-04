package az.turing.authserver.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListener {

    @RabbitListener(queues = RabbitMQConfig.USER_REGISTERED_QUEUE)
    public void receiveUserRegisteredMessage(String message) {
        System.out.println("User registered message: " + message);
    }

    @RabbitListener(queues = RabbitMQConfig.USER_LOGIN_QUEUE)
    public void receiveUserLoginMessage(String message) {
        System.out.println("User login message: " + message);
    }
}