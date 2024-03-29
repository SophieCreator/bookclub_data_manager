package com.bookclub_data_manager.repository;

import com.bookclub_data_manager.models.Expense;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Integer> {


    @Query(value = "SELECT * FROM expenses", nativeQuery = true)
    List<Expense> getAllExpenses();

    @Query(value = "SELECT * FROM expenses WHERE expense_id = :expense_id", nativeQuery = true)
    Expense getExpenseById(@Param("expense_id")int expense_id);

    @Query(value = "SELECT SUM(amount) FROM expenses WHERE (date_get >= dateStart) AND (date_get <= dateEnd)", nativeQuery = true)
    int getExpenseSum(Date dateStart, Date dateEnd);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO expenses (name, amount, date_get, is_regular) VALUES (:name, :amount, :date_get, :is_regular)", nativeQuery = true)
    void add(@Param("name")String name, @Param("amount")int amount, @Param("date_get") Date date_get, @Param("is_regular") String is_regular);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM expenses WHERE expense_id = :expense_id", nativeQuery = true)
    void delete(@Param("expense_id")int expense_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE expenses SET name = :name, amount = :amount, date_get = :date_get, is_regular = :is_regular WHERE expense_id = :expense_id", nativeQuery = true)
    void update(@Param("name")String name, @Param("amount")int amount, @Param("date_get") Date date_get, @Param("is_regular") String is_regular, @Param("expense_id")int expense_id);



}
