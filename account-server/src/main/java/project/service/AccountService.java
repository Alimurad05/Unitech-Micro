package project.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import project.Repository.AccountRepository;
import project.dto.AccountResponse;
import project.dto.CreateAccountRequest;
import project.model.Account;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public AccountResponse createAccount(CreateAccountRequest request) {
        Account account = Account.builder()
                .userId(Long.parseLong(request.getUserId()))
                .currency(request.getCurrency())
                .iban(generateIban())
                .balance(BigDecimal.ZERO) // Changed to BigDecimal.ZERO
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .active(true)
                .build();

        Account saved = accountRepository.save(account);

        return mapToResponse(saved);
    }

    public List<AccountResponse> getActiveAccountsByUserId(String userId) {
        List<Account> accounts = accountRepository.findByUserIdAndActiveTrue(Long.parseLong(userId));
        return accounts.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public List<AccountResponse> getAllAccountsByUserId(String userId) {
        List<Account> accounts = accountRepository.findByUserId(Long.parseLong(userId));
        return accounts.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private AccountResponse mapToResponse(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .userId(account.getUserId())
                .currency(account.getCurrency())
                .balance(account.getBalance()) // No conversion needed, already BigDecimal
                .isActive(account.isActive())
                .build();
    }

    private String generateIban() {
        return "AZ" + UUID.randomUUID().toString().substring(0, 24).replace("-", "").toUpperCase();
    }
    public void debit(Long id, BigDecimal amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);
    }

    public void credit(Long id, BigDecimal amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
    }

}