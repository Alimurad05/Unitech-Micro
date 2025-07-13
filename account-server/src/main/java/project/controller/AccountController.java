package project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.dto.AccountResponse;
import project.dto.CreateAccountRequest;
import project.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")

public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@RequestBody @Valid CreateAccountRequest request) {
        return ResponseEntity.ok(accountService.createAccount(request));
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AccountResponse>> getAllByUser(@PathVariable String userId) {
        return ResponseEntity.ok(accountService.getAllAccountsByUserId(userId));
    }
    @GetMapping("/user/{userId}/active")
    public ResponseEntity<List<AccountResponse>> getActiveByUser(@PathVariable String userId) {
        return ResponseEntity.ok(accountService.getActiveAccountsByUserId(userId));
    }
}
