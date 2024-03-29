package com.bookclub_data_manager.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rewards")
public class Reward {

    @Id
    private int reward_id;
    private String name;
    private String reason;
    private String promo;
    private String image;
    private int tests_required;
    private int meetings_required;
    private int sale;

    public int getReward_id() {
        return reward_id;
    }

    public void setReward_id(int reward_id) {
        this.reward_id = reward_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getTests_required() {
        return tests_required;
    }

    public void setTests_required(int tests_required) {
        this.tests_required = tests_required;
    }

    public int getMeetings_required() {
        return meetings_required;
    }

    public void setMeetings_required(int meetings_required) {
        this.meetings_required = meetings_required;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }
}
