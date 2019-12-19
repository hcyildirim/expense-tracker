package com.huseyin.expensetracker.filters;

import com.huseyin.expensetracker.interfaces.ICriteria;
import com.huseyin.expensetracker.models.Transaction;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class Income implements ICriteria {
    @Override
    public List<Transaction> meets(List<Transaction> transactions) {
        return transactions.stream().filter(transaction -> transaction.getType().equals(Transaction.Type.Income)).collect(toList());
    }
}