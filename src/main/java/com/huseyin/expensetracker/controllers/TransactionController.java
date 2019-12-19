package com.huseyin.expensetracker.controllers;

import com.huseyin.expensetracker.models.Transaction;
import com.huseyin.expensetracker.repositories.TransactionRepository;
import com.huseyin.expensetracker.repositories.UserRepository;
import com.huseyin.expensetracker.factories.FilterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Controller
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping({"/", "/transactions"})
    public String transactions(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(required = false, name = "filter") String filter, Model model) {
        model.addAttribute("incomeForm", new Transaction());
        model.addAttribute("outcomeForm", new Transaction());

        List<Transaction> transactions;

        if (filter == null) {
            transactions = transactionRepository.findByUsername(userDetails.getUsername());
        } else {
            transactions = new FilterFactory().getFilter(filter).meets(transactionRepository.findByUsername(userDetails.getUsername()));
        }

        BigDecimal sum = transactions.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("transactions", transactions);
        model.addAttribute("sum", sum);

        return "transactions";
    }

    @PostMapping("/income")
    public String income(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute("incomeForm") Transaction incomeForm, BindingResult bindingResult) {
        incomeForm.setUser(userRepository.findByUsername(userDetails.getUsername()));
        incomeForm.setType(Transaction.Type.Income);
        incomeForm.setCreatedAt(new Date());
        transactionRepository.save(incomeForm);

        return "redirect:/transactions";
    }

    @PostMapping("/outcome")
    public String outcome(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute("outcomeForm") Transaction outcomeForm, BindingResult bindingResult) {
        outcomeForm.setUser(userRepository.findByUsername(userDetails.getUsername()));
        outcomeForm.setType(Transaction.Type.Outcome);
        outcomeForm.setCreatedAt(new Date());
        transactionRepository.save(outcomeForm);

        return "redirect:/transactions";
    }
}