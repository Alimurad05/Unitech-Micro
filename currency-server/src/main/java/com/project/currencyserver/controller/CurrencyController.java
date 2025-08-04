package com.project.currencyserver.controller;

import com.project.currencyserver.model.Currency;
import com.project.currencyserver.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/currencies")
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public ResponseEntity<List<Currency>> getAllCurrencies() {
        return ResponseEntity.ok(currencyService.getAllCurrencies());
    }

    @GetMapping("/{source}/{target}")
    public ResponseEntity<Currency> getCurrencyRate(@PathVariable String source, @PathVariable String target) {
        return ResponseEntity.ok(currencyService.getCurrencyRate(source, target));
    }
    @GetMapping("/convert")
    public ResponseEntity<BigDecimal> convertCurrency(@RequestParam String source, @RequestParam String target, @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(currencyService.convertCurrency(source, target, amount));
    }
}
