package com.bookclub_data_manager.repository;

import com.bookclub_data_manager.models.Income;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface IncomeRepository extends CrudRepository<Income, Integer> {

    @Query(value = "SELECT * FROM incomes", nativeQuery = true)
    List<Income> getAllIncomes();

    @Query(value = "SELECT * FROM incomes WHERE income_id = :income_id", nativeQuery = true)
    Income getIncomeById(@Param("income_id")int income_id);

    @Query(value = "SELECT SUM(amount) FROM incomes WHERE (date_get >= dateStart) AND (date_get <= dateEnd)", nativeQuery = true)
    int getIncomeSum(Date dateStart, Date dateEnd);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO incomes (name, amount, date_get) VALUES (:name, :amount, :date_get)", nativeQuery = true)
    void add(@Param("name")String name, @Param("amount")int amount, @Param("date_get") Date date_get);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM incomes WHERE income_id = :income_id", nativeQuery = true)
    void delete(@Param("income_id")int income_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE incomes SET name = :name, amount = :amount, date_get = :date_get WHERE income_id = :income_id", nativeQuery = true)
    void update(@Param("name")String name, @Param("amount")int amount, @Param("date_get") Date date_get, @Param("income_id")int income_id);


}
