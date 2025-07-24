package com.project.currencyserver.service;

import com.project.currencyserver.exception.CurrencyNotFoundException;
import com.project.currencyserver.exception.NotValidCurrency;
import com.project.currencyserver.model.Currency;
import com.project.currencyserver.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor

public class CurrencyService {

    private  final CurrencyRepository currencyRepository;

    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    public Currency getCurrencyRate(String source, String target) {
        return currencyRepository.findBySourceCurrencyAndTargetCurrency(source, target)
                .orElseThrow(() ->
                        new CurrencyNotFoundException("Currency rate not found from " + source + " to " + target));
    }
    public BigDecimal convertCurrency(String source, String target, BigDecimal amount) {
        Currency currency = currencyRepository.findBySourceCurrencyAndTargetCurrency(source, target)
                .orElseThrow(() -> new NotValidCurrency("Currency not found from " + source + " to " + target));

        return amount.multiply(currency.getRate());
    }
}

