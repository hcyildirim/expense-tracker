package com.huseyin.expensetracker.repositories;

import com.huseyin.expensetracker.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("select t from Transaction t inner join User u on u.id = t.user.id where u.username = :username")
    List<Transaction> findByUsername(String username);

    @Query("select t from Transaction t inner join User u on u.id = t.user.id where u.username = :username and t.description like %:query% ")
    List<Transaction> findByDescriptionContaining(String username, String query);
}