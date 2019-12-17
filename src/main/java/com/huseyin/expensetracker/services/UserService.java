package com.huseyin.expensetracker.services;

import com.huseyin.expensetracker.models.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}