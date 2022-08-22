package com.example.crud.controllers;

import com.example.crud.models.User;
import com.example.crud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/get")
    public ArrayList<User> getUsers(){
        return this.userService.getUsers();
    }

    @GetMapping("/get/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return this.userService.getUserById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        ArrayList<String> errors = new ArrayList<String>();
        if (user.getEmail() == null) {
            errors.add("Email cannot be null");
        }
        if (user.getName() == null) {
            errors.add("Name cannot be null");
        }
        if (user.getTelephone() == null) {
            errors.add("Telephone cannot be null");
        }
        if (user.getPhone() == null) {
            errors.add("Phone cannot be null");
        }
        if (!errors.isEmpty()) {
            return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
        }
        this.userService.createUser(user);
        return new ResponseEntity<User>(user,null,HttpStatus.CREATED);
    }


}
