package com.huseyin.expensetracker.repositories;

import com.huseyin.expensetracker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}