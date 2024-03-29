package com.bookclub_data_manager.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;

@Entity
@Table(name = "incomes")
public class Income {

    @Id
    private int income_id;
    private String name;
    private int amount;
    private Date date_get;

    public int getIncome_id() {
        return income_id;
    }

    public void setIncome_id(int income_id) {
        this.income_id = income_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate_get() {
        return date_get;
    }

    public void setDate_get(Date date_get) {
        this.date_get = date_get;
    }

    public int getSum() {
        return amount;
    }

    public void setSum(int amount) {
        this.amount = amount;
    }
}
