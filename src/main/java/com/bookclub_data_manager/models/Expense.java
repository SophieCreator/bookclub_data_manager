package com.bookclub_data_manager.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;

@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    private int expense_id;
    private String name;

    private String url;
    private Date date_get;

    private int amount;
    String is_regular;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIncome_id() {
        return expense_id;
    }

    public void setIncome_id(int income_id) {
        this.expense_id = income_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date_get;
    }

    public void setDate(Date date_get) {
        this.date_get = date_get;
    }

    public int getSum() {
        return amount;
    }

    public void setSum(int amount) {
        this.amount = amount;
    }

    public String getIs_regular() {
        return is_regular;
    }

    public void setIs_regular(String is_regular) {
        this.is_regular = is_regular;
    }
}
