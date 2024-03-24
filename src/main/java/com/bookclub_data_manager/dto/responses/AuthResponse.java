package com.bookclub_data_manager.dto.responses;

import com.bookclub_data_manager.services.auth.MyCustomUserDetails;

public class AuthResponse {

    private MyCustomUserDetails myCustomUserDetails;
    private String token;

    public AuthResponse(){

    }

    public AuthResponse(String token, MyCustomUserDetails myCustomUserDetails){
        this.token = token;
        this.myCustomUserDetails = myCustomUserDetails;
    }

    public int getUserId(){
        return this.myCustomUserDetails.getUserId();
    }

    public String getEmail(){
        return this.myCustomUserDetails.getUsername();
    }

    public String getName(){
        return this.myCustomUserDetails.getName();
    }

    public String getLogin(){
        return this.myCustomUserDetails.getLogin();
    }

    public String getIsAdmin(){
        return this.myCustomUserDetails.getIsAdmin();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
