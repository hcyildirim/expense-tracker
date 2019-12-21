package com.huseyin.expensetracker.controllers;

import com.huseyin.expensetracker.models.Transaction;
import com.huseyin.expensetracker.services.TransactionService;
import com.huseyin.expensetracker.services.UserService;
import com.huseyin.expensetracker.factories.FilterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @GetMapping({"/", "/transactions"})
    public String transactions(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(required = false, name = "filter") String filter, Model model) {
        model.addAttribute("incomeForm", new Transaction());
        model.addAttribute("outcomeForm", new Transaction());

        List<Transaction> transactions = transactionService.findByUsername(userDetails.getUsername());

        if (filter != null) {
            transactions = FilterFactory.getInstance().getFilter(filter).meets(transactions);
        }

        model.addAttribute("transactions", transactions);
        model.addAttribute("sum", transactionService.sum(transactions));

        return "transactions";
    }

    @PostMapping("/income")
    public String income(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute("incomeForm") Transaction incomeForm, BindingResult bindingResult) {
        incomeForm.setUser(userService.findByUsername(userDetails.getUsername()));
        incomeForm.setType(Transaction.Type.Income);
        incomeForm.setCreatedAt(new Date());
        transactionService.save(incomeForm);

        return "redirect:/transactions";
    }

    @PostMapping("/outcome")
    public String outcome(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute("outcomeForm") Transaction outcomeForm, BindingResult bindingResult) {
        outcomeForm.setUser(userService.findByUsername(userDetails.getUsername()));
        outcomeForm.setType(Transaction.Type.Outcome);
        outcomeForm.setCreatedAt(new Date());
        transactionService.save(outcomeForm);

        return "redirect:/transactions";
    }

    @GetMapping(value = "delete/{id}")
    public String delete(@PathVariable Long id) {
        transactionService.deleteById(id);

        return "redirect:/transactions";
    }

    @GetMapping("/search")
    public String search(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(name = "query") String query, Model model) {
        List<Transaction> transactions = transactionService.findByDescriptionContaining(userDetails.getUsername(), query);
        model.addAttribute("transactions", transactions);
        model.addAttribute("sum", transactionService.sum(transactions));

        return "transactions";
    }
}