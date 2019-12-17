package com.huseyin.expensetracker.controllers;

import com.huseyin.expensetracker.models.Transaction;
import com.huseyin.expensetracker.repositories.TransactionRepository;
import com.huseyin.expensetracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/income")
    public String income(Model model) {
        model.addAttribute("incomeForm", new Transaction());

        return "income";
    }

    @PostMapping("/income")
    public String income(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute("incomeForm") Transaction incomeForm, BindingResult bindingResult) {
        incomeForm.setUser(userRepository.findByUsername(userDetails.getUsername()));
        incomeForm.setType(Transaction.Type.Income);
        transactionRepository.save(incomeForm);

        return "redirect:/welcome";
    }


    @GetMapping("/outcome")
    public String outcome(Model model) {
        model.addAttribute("outcomeForm", new Transaction());

        return "outcome";
    }
}