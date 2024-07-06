package com.anshu.journalApp.controller;

import java.util.List;

import com.anshu.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.anshu.journalApp.service.UserService;

@RestController // Rest Controller handles HTTP requests and return JSON response(generally)
@RequestMapping("/user")
public class UserController {

    
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAll();

    }
    
    @PostMapping
    public void createUser(@RequestBody User user){
        userService.saveEntry(user);
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable String username){
        User userIndb=userService.findByUsername(username);
        if(userIndb!=null){
            userIndb.setUsername(user.getUsername());
            userIndb.setPassword(user.getPassword());
            userService.saveEntry(userIndb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

