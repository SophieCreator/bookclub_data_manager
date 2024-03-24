package com.bookclub_data_manager.services.auth;

import com.bookclub_data_manager.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyCustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String emailOrLogin) throws UsernameNotFoundException {
        User user = userService.getUserByEmailOrLogin(emailOrLogin);
        if(user == null){
            throw new UsernameNotFoundException("Пользователь по такой почте или логину не найден");
        }
        return new MyCustomUserDetails(user);
    }

}

