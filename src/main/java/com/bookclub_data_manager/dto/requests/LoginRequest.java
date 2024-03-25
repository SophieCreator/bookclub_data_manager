package com.bookclub_data_manager.dto.requests;

public class LoginRequest {

    private String emailOrLogin;
    private String password;

    public String getEmailOrLogin() {
        return emailOrLogin;
    }

    public void setEmailOrLogin(String emailOrLogin) {
        this.emailOrLogin = emailOrLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}