package com.example.internallearningtesttask.user.service;

import com.example.internallearningtesttask.externalhrsource.ExternalHrSource;
import com.example.internallearningtesttask.externalhrsource.repository.ExternalHrSourceRepository;
import com.example.internallearningtesttask.user.User;
import com.example.internallearningtesttask.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ExternalHrSourceRepository externalHrSourceRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository, ExternalHrSourceRepository externalHrSourceRepository) {
        this.userRepository = userRepository;
        this.externalHrSourceRepository = externalHrSourceRepository;
    }

    @Override
    @Transactional
    public List<User> syncUsersWithExternalHrSources() {
        List<ExternalHrSource> externalHrSources = externalHrSourceRepository.findAll();

        if (externalHrSources.isEmpty()) {
            return Collections.emptyList();
        }

        List<User> updatedUsers = new ArrayList<>();
        externalHrSources.forEach(externalHrSource -> {
            User user = userRepository.findUserByEmail(externalHrSource.getEmail());
            if (user != null) {
                user.setEmail(externalHrSource.getEmail());
                user.setFirstname(externalHrSource.getFirstname());
                user.setLastname(externalHrSource.getLastname());

                updatedUsers.add(userRepository.save(user));
            }
        });

        return updatedUsers;
    }

    @Override
    @Transactional
    public List<User> nullifyUserFirstAndLastName() {
        List<User> users = userRepository.findUserByFirstnameNotNullAndLastnameNotNull();

        if (users.isEmpty()) {
            return Collections.emptyList();
        }

        users.forEach(user -> {
            user.setLastname(null);
            user.setFirstname(null);

            userRepository.save(user);
            logger.info("The user {} was nullified", user.getEmail());
        });

        return users;
    }
}

