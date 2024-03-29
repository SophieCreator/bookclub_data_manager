package com.bookclub_data_manager.controllers;

import com.bookclub_data_manager.models.Expense;
import com.bookclub_data_manager.services.budget.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("app/expense")
public class ExpenseController {
    
    @Autowired
    ExpenseService expenseService;

    @PostMapping("/add")
    public ResponseEntity addExpense(@RequestParam String name,
                                     @RequestParam int amount,
                                     @RequestParam Date date,
                                     @RequestParam String is_regular){


        String request = expenseService.add(name,amount, date, is_regular);
        if (Objects.equals(request, "OK")) {
            return new ResponseEntity("Расход успешно добавлен", HttpStatus.CREATED);
        } else {
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity deleteExpense(@RequestParam int expense_id){

        String request = expenseService.delete(expense_id);
        if (Objects.equals(request, "OK")) {
            return new ResponseEntity("Расход успешно удалён", HttpStatus.OK);
        } else {
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update")
    public ResponseEntity updateExpense(@RequestParam String name, 
                                        @RequestParam int amount, 
                                        @RequestParam Date date, 
                                        @RequestParam String is_regular, 
                                        @RequestParam int expense_id){


        String request = expenseService.update(name,amount, date, is_regular, expense_id);
        if (Objects.equals(request, "OK")) {
            return new ResponseEntity("Расход успешно обновлен", HttpStatus.OK);
        } else {
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/get")
    public ResponseEntity getExpense(@RequestParam int expense_id){

        Expense expense = expenseService.getExpenseById(expense_id);
        if (expense != null) {
            return new ResponseEntity(expense, HttpStatus.OK);
        } else {
            return new ResponseEntity("Не удалось получить данные", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/getAll")
    public ResponseEntity getAllExpenses(){

        List<Expense> expenses = expenseService.getExpenses();
        if (expenses != null) {
            return new ResponseEntity(expenses, HttpStatus.OK);
        } else {
            return new ResponseEntity("Не удалось получить данные", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/sum")
    public ResponseEntity sum(@RequestParam Date start,
                              @RequestParam Date end){

        int sum = expenseService.getExpenseSum(start, end);
        return new ResponseEntity(sum, HttpStatus.OK);

    }

}
