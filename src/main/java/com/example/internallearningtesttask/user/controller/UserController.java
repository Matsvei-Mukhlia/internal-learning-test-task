package com.example.internallearningtesttask.user.controller;

import com.example.internallearningtesttask.user.User;
import com.example.internallearningtesttask.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sync")
    public ResponseEntity<List<User>> syncUsersWithExternalHrSources() {
        return new ResponseEntity<>(userService.syncUsersWithExternalHrSources(), HttpStatus.OK);
    }

    @PutMapping("/clear")
    public ResponseEntity<List<User>> nullifyUserFirstAndLastName() {
        return new ResponseEntity<>(userService.nullifyUserFirstAndLastName(), HttpStatus.OK);
    }
}
