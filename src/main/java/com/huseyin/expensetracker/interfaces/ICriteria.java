package com.huseyin.expensetracker.interfaces;

import com.huseyin.expensetracker.models.Transaction;
import java.util.List;

public interface ICriteria {
    List<Transaction> meets(List<Transaction> transactions);
}