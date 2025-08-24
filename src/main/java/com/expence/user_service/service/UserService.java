package com.expence.user_service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expence.user_service.dto.UserResponse;
import com.expence.user_service.model.User;
import com.expence.user_service.repo.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public Boolean exitsByUserId(String userId){
        log.info("Checking existence of user with ID: {}", userId);
        return userRepo.existsById(userId);
    }

    public UserResponse getUserById(String userId){
        log.info("Fetching user with ID: {}", userId);
        User user = userRepo.findById(userId)
                    .orElseThrow(()->new RuntimeException("User not found."));

        log.info(user.getUserId());
        UserResponse userResponse = mapToUserResponse(user);
        return userResponse;
    }

    public UserResponse registerUser(UserResponse userResponse){
        Optional<User> existingUser = userRepo.findById(userResponse.getUserId());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User savedUser = new User();
        savedUser.setUserId(userResponse.getUserId());
        savedUser.setFirstName(userResponse.getFirstName());
        savedUser.setLastName(userResponse.getLastName());
        savedUser.setEmail(userResponse.getEmail());
        savedUser.setProfilePicUrl(userResponse.getProfilePicUrl());
        userRepo.save(savedUser);
        log.info("User with ID: {} registered successfully.", savedUser.getUserId());
        return mapToUserResponse(savedUser);
    }

    public UserResponse mapToUserResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getUserId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setProfilePicUrl(user.getProfilePicUrl());
        return userResponse;
    }
}
