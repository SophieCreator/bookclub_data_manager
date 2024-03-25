package com.bookclub_data_manager.controllers;

import com.bookclub_data_manager.dto.requests.LoginRequest;
import com.bookclub_data_manager.dto.responses.AuthResponse;
import com.bookclub_data_manager.services.JwtTokenService;
import com.bookclub_data_manager.services.auth.MyCustomUserDetailService;
import com.bookclub_data_manager.services.auth.MyCustomUserDetails;
import com.bookclub_data_manager.services.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("app/user")
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private MyCustomUserDetailService myCustomUserDetailService;
    @Autowired
    private JwtTokenService jwtTokenService;

    @PostMapping("/authentication/register")
    public ResponseEntity register(@RequestParam("name") String name,
                                 @RequestParam("login") String login,
                                 @RequestParam("email") String email,
                                 @RequestParam("password") String password,
                                 @RequestParam("is_admin") String is_admin){

        String hashed_password = passwordEncoder.encode(password);

        String result = userService.registerUser(name, login, email, hashed_password, is_admin);

        if(!Objects.equals(result, "1")){
            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Вы успешно зарегистрированы!", HttpStatus.CREATED);
    }

    @PostMapping("/authentication/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest){
        AuthResponse response = null;
        String error = "";

        String emailOrLogin = loginRequest.getEmailOrLogin();
        String password = loginRequest.getPassword();

        if(!userService.doesEmailOrLoginExist(emailOrLogin)){
            return new ResponseEntity("Логин или почта не существуют", HttpStatus.BAD_REQUEST);
        }

        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(emailOrLogin, password));
            MyCustomUserDetails userDetails =
                    (MyCustomUserDetails) myCustomUserDetailService.loadUserByUsername(emailOrLogin);


            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtTokenService.generateToken(userDetails);

            response = new AuthResponse(token, userDetails);
        } catch (Exception e){
            error = e.getMessage();
        }
         if(Objects.equals(error, "")){
             return new ResponseEntity(response, HttpStatus.ACCEPTED);
         } else {
             return new ResponseEntity("Неверный пароль", HttpStatus.BAD_REQUEST);
         }
    }


}