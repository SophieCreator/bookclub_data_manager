package com.bookclub_data_manager.controllers;

import com.bookclub_data_manager.dto.requests.LoginRequest;
import com.bookclub_data_manager.dto.responses.AuthResponse;
import com.bookclub_data_manager.dto.responses.BookCardResponse;
import com.bookclub_data_manager.dto.responses.UserInfoResponse;
import com.bookclub_data_manager.models.Author;
import com.bookclub_data_manager.models.Book;
import com.bookclub_data_manager.models.Genre;
import com.bookclub_data_manager.models.User;
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

import java.util.ArrayList;
import java.util.List;
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

    @PostMapping("/update")
    public ResponseEntity update(@RequestParam("name") String name,
                                 @RequestParam("login") String login,
                                 @RequestParam("email") String email,
                                 @RequestParam("password") String password,
                                 @RequestParam("user_id") int user_id){

        String hashed_password = passwordEncoder.encode(password);
        String result = userService.update(name, login, email, hashed_password, user_id);

        if(!Objects.equals(result, "OK")){
            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Вы успешно обновили профиль!", HttpStatus.CREATED);
    }

    @PostMapping("/delete")
    public ResponseEntity delete(@RequestParam("user_id") int user_id){
        String request = userService.delete(user_id);
        if(!Objects.equals(request, "OK")){
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Успешно удалено", HttpStatus.OK);
    }

    @PostMapping("/get")
    public ResponseEntity get(@RequestParam("user_id") Integer user_id){
        User user = userService.getUserById(user_id);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PostMapping("/getAll")
    public ResponseEntity getAll(){
        List<User> users = userService.getAll();
        if (users.isEmpty()){
            return new ResponseEntity("Список пустой", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @PostMapping("/getAllInfo")
    public ResponseEntity getAllInfo(){
        List<User> users = userService.getAll();
        List<UserInfoResponse> userInfoResponses = new ArrayList<>();

        for (User user : users){
            List<Book> books = userService.getFavouriteBooksById(user.getUser_id());
            List<Author> authors = userService.getFavouriteAuthorsById(user.getUser_id());
            List<Genre> genres = userService.getFavouriteGenresById(user.getUser_id());
            String visited_meetings = userService.visited(user.getUser_id());

            UserInfoResponse userInfoResponse = new UserInfoResponse(user, books, authors, genres, visited_meetings);
            userInfoResponses.add(userInfoResponse);
        }

        if(userInfoResponses == null){
            return new ResponseEntity("Нет пользователей", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(userInfoResponses, HttpStatus.OK);
        }
    }

    @PostMapping("/getUsersByIds")
    public ResponseEntity getUsersByIds(@RequestParam("user_ids") List<Integer> ids){
        List<User> users = userService.getUserByIds(ids);
        return new ResponseEntity(users, HttpStatus.OK);
    }


}