package com.bookclub_data_manager.services.budget;

import com.bookclub_data_manager.models.Genre;
import com.bookclub_data_manager.models.Income;
import com.bookclub_data_manager.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class IncomeService {
    
    @Autowired
    IncomeRepository incomeRepository;

    public String add(String name, Integer amount, Date date){
        if (name == null || amount == null){
            return "Пустая строка";
        }
        incomeRepository.add(name, amount, date);
        return "OK";
    }

    public String delete(Integer id){
        if (id == null){
            return "Нет идентификатора";
        }
        incomeRepository.delete(id);
        return "OK";
    }

    public String update(String name, int amount, Date date, Integer id){
        if (id == null){
            return "Нет идентификатора";
        }
        incomeRepository.update(name, amount, date, id);
        return "OK";
    }

    public List<Income> getIncomes(){
        return incomeRepository.getAllIncomes();
    }

    public Income getIncomeById(int id){
        return incomeRepository.getIncomeById(id);
    }

    public int getIncomeSum(Date dateStart, Date dateEnd){
        return incomeRepository.getIncomeSum(dateStart, dateEnd);
    }


}
