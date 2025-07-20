package az.turing.transfer2server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequestDTO {
    private Long fromAccountId;
    private Long toAccountId;
    private BigDecimal amount;

}
