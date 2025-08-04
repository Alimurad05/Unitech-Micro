package az.turing.transfer2server.controller;

import az.turing.transfer2server.dto.TransferRequestDTO;
import az.turing.transfer2server.dto.TransferResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import az.turing.transfer2server.service.TransferService;

@RestController
@RequestMapping("/api/transfers")
@RequiredArgsConstructor
public class TransferController {
    private  TransferService transferService;
    @PostMapping
    public ResponseEntity<TransferResponse> transfer(@RequestBody @Valid TransferRequestDTO request) {
        return ResponseEntity.ok(transferService.transfer(request));
    }

}
