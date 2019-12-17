package com.huseyin.expensetracker.interfaces;

import com.huseyin.expensetracker.models.User;

public interface IUserService {
    void save(User user);

    User findByUsername(String username);
}