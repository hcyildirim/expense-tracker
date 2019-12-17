package com.huseyin.expensetracker.interfaces;

public interface ISecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}