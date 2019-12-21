package com.huseyin.expensetracker.interfaces;

import com.huseyin.expensetracker.models.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface ITransactionService {
    void save(Transaction transaction);

    void deleteById(Long id);

    List<Transaction> findByUsername(String username);

    BigDecimal sum(List<Transaction> transactions);
}