package com.example.internallearningtesttask.user.service;

import com.example.internallearningtesttask.user.User;

import java.util.List;

public interface UserService {
    List<User> syncUsersWithExternalHrSources();

    List<User> nullifyUserFirstAndLastName();
}
