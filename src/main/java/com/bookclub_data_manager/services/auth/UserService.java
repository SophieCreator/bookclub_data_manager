package com.bookclub_data_manager.services.auth;

import com.bookclub_data_manager.models.User;
import com.bookclub_data_manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public User getUserByEmailOrLogin(String emailOrLogin){

        User userByEmail = userRepository.getUserByEmail(emailOrLogin);
        User userByLogin = userRepository.getUserByLogin(emailOrLogin);

        if (userByEmail != null){
            return userByEmail;
        } else {
            return userByLogin;
        }
    }

    public List<String> doesEmailExist(String email){
        return userRepository.doesEmailExist(email);
    }

    public List<String> doesLoginExist(String login){
        return userRepository.doesLoginExist(login);
    }

    public boolean doesEmailOrLoginExist(String emailOrLogin){
        if (doesLoginExist(emailOrLogin).isEmpty() && doesEmailExist(emailOrLogin).isEmpty()){
            return false;
        }
        return true;
    }
    

    public String registerUser(String name, String login, String email, String password, String is_admin){
        if (name.isEmpty() || login.isEmpty() || email.isEmpty() || password.isEmpty()){
            return "Поле не может быть пустым";
        }
        if (!doesEmailExist(email).isEmpty()){
            return "Почта уже занята";
        }
        if (!doesLoginExist(login).isEmpty()){
            return "Логин уже занят";
        }

        return String.valueOf(userRepository.registerUser(name, login, email, password, is_admin));

    }
}
