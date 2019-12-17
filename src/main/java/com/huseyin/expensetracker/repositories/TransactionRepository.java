package com.huseyin.expensetracker.repositories;

import com.huseyin.expensetracker.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}