package com.bookclub_data_manager.services.auth;

import com.bookclub_data_manager.models.User;
import com.bookclub_data_manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;

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

    public String update(String name, String login, String email, String password, int user_id){
        if (name.isEmpty() || login.isEmpty() || email.isEmpty() || password.isEmpty()){
            return "Поле не может быть пустым";
        }
        if (!emailIsMine(email, user_id)){
            if (!doesEmailExist(email).isEmpty()){
                return "Почта уже занята";
            }
        }
        if (!loginIsMine(login, user_id)){
            if (!doesLoginExist(login).isEmpty()){
                return "Логин уже занят";
            }
        }

        userRepository.update(name, login, email, password, user_id);
        return "OK";
    }

    public String delete(int user_id){
        if (!userRepository.existsById(user_id)){
            return "Пользователь не существует";
        }
        userRepository.delete(user_id);
        return "OK";
    }

    public List<User> getAll(){
        return userRepository.getAllUsers();
    }

    public User getUserById(int user_id){
        return userRepository.getUserById(user_id);
    }

    public boolean emailIsMine(String email, int user_id){
        List<String> emailById = userRepository.emailIsMine(user_id, email);
        if (emailById.isEmpty()){
            return false;
        }
        return true;
    }

    public boolean loginIsMine(String login, int user_id){
        List<String> loginById = userRepository.loginIsMy(user_id, login);
        if (loginById.isEmpty()){
            return false;
        }
        return true;
    }

    public boolean matchPassword(String password, int user_id){
        String real = userRepository.getPassword(user_id);
        if (Objects.equals(real, password)){
            return true;
        }
        return false;
    }

}
