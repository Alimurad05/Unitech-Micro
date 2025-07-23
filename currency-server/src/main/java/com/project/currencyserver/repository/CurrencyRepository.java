package com.project.currencyserver.repository;

import com.project.currencyserver.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Optional<Currency> findBysourceCurrencyAndtargetCurrency(String from, String to);
}

