package az.turing.transfer2server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Transfer2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Transfer2ServerApplication.class, args);
    }

}
