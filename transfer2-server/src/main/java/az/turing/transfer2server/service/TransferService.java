package az.turing.transfer2server.service;

import az.turing.transfer2server.dto.TransferRequestDTO;
import az.turing.transfer2server.dto.TransferResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import az.turing.transfer2server.client.AccountClient;


@Service
@RequiredArgsConstructor
public class TransferService {

    private final AccountClient accountClient;

    public TransferResponse transfer(TransferRequestDTO request) {
        accountClient.debit(request.getFromAccountId(), request.getAmount());
        accountClient.credit(request.getToAccountId(), request.getAmount());

        return new TransferResponse(
                request.getFromAccountId(),
                request.getToAccountId(),
                request.getAmount(),
                "SUCCESS"
        );
    }
}
