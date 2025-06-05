package com.app.college.collegeApp.controllers;

import com.app.college.collegeApp.models.Users;
import com.app.college.collegeApp.repository.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    UsersRepository usersRepository;
    ObjectMapper objectMapper;

    @Autowired
    public UsersController(UsersRepository usersRepository, ObjectMapper objectMapper) {
        this.usersRepository = usersRepository;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/")
    public ResponseEntity<Users[]> getUsers() {
        Users[] users = this.objectMapper.convertValue(this.usersRepository.findAll(), Users[].class);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<Users> addNewUser(@RequestBody Users users) {
        Users newUser = this.usersRepository.saveAndFlush(users);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }
}
