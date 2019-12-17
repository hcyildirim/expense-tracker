package com.huseyin.expensetracker.services;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}