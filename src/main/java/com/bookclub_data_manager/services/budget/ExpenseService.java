package com.bookclub_data_manager.services.budget;

import com.bookclub_data_manager.models.Expense;
import com.bookclub_data_manager.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class ExpenseService {
    
    @Autowired
    ExpenseRepository expenseRepository;

    public String add(String name, Integer amount, Date date, String is_regular){
        if (name == null || amount == null){
            return "Пустая строка";
        }
        expenseRepository.add(name, amount, date, is_regular);
        return "OK";
    }

    public String delete(Integer id){
        if (id == null){
            return "Нет идентификатора";
        }
        expenseRepository.delete(id);
        return "OK";
    }

    public String update(String name, int amount, Date date, String is_regular, Integer id){
        if (id == null){
            return "Нет идентификатора";
        }
        expenseRepository.update(name, amount, date, is_regular, id);
        return "OK";
    }

    public List<Expense> getExpenses(){
        return expenseRepository.getAllExpenses();
    }

    public Expense getExpenseById(int id){
        return expenseRepository.getExpenseById(id);
    }

    public int getExpenseSum(Date dateStart, Date dateEnd){
        return expenseRepository.getExpenseSum(dateStart, dateEnd);
    }
    
}
