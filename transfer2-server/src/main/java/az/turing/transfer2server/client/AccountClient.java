package az.turing.transfer2server.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.UUID;

@FeignClient(name = "account-server", path = "/api/accounts")
public interface AccountClient {

    @PutMapping("/{id}/debit")
    void debit(@PathVariable("id") Long id, @RequestParam("amount") BigDecimal amount);

    @PutMapping("/{id}/credit")
    void credit(@PathVariable("id") Long id, @RequestParam("amount") BigDecimal amount);
}
