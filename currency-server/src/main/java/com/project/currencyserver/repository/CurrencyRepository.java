package com.project.currencyserver.repository;

import com.project.currencyserver.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Optional<Currency> findBySourceCurrencyAndTargetCurrency(String from, String to);
}

