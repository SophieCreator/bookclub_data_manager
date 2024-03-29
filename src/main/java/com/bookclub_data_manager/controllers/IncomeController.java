package com.bookclub_data_manager.controllers;

import com.bookclub_data_manager.models.Income;
import com.bookclub_data_manager.services.budget.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("app/income")
public class IncomeController {

    @Autowired
    IncomeService incomeService;

    @PostMapping("/add")
    public ResponseEntity addIncome(@RequestParam String name,
                                    @RequestParam int amount,
                                    @RequestParam Date date){


        String request = incomeService.add(name,amount, date);
        if (Objects.equals(request, "OK")) {
            return new ResponseEntity("Доход успешно добавлен", HttpStatus.CREATED);
        } else {
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity deleteIncome(@RequestParam int income_id){

        String request = incomeService.delete(income_id);
        if (Objects.equals(request, "OK")) {
            return new ResponseEntity("Доход успешно удалён", HttpStatus.OK);
        } else {
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update")
    public ResponseEntity updateIncome(@RequestParam String name,
                                    @RequestParam int amount,
                                    @RequestParam Date date,
                                    @RequestParam int income_id){


        String request = incomeService.update(name,amount, date, income_id);
        if (Objects.equals(request, "OK")) {
            return new ResponseEntity("Доход успешно обновлен", HttpStatus.OK);
        } else {
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/get")
    public ResponseEntity getIncome(@RequestParam int income_id){

        Income income = incomeService.getIncomeById(income_id);
        if (income != null) {
            return new ResponseEntity(income, HttpStatus.OK);
        } else {
            return new ResponseEntity("Не удалось получить данные", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/getAll")
    public ResponseEntity getAllIncomes(){

        List<Income> incomes = incomeService.getIncomes();
        if (incomes != null) {
            return new ResponseEntity(incomes, HttpStatus.OK);
        } else {
            return new ResponseEntity("Не удалось получить данные", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/sum")
    public ResponseEntity sum(@RequestParam Date start,
                              @RequestParam Date end){

        int sum = incomeService.getIncomeSum(start, end);
        return new ResponseEntity(sum, HttpStatus.OK);

    }

}
