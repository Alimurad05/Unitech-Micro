package az.turing.transfer2server.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TransferResponse {
    private Long fromAccountId;
    private Long toAccountId;
    private BigDecimal amount;
    private String status;
}
