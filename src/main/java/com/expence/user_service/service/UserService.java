package com.expence.user_service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expence.user_service.dto.UserResponse;
import com.expence.user_service.model.User;
import com.expence.user_service.repo.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public Boolean exitsByUserId(String userId){
        return userRepo.existsById(userId);
    }

    public UserResponse getUserById(String userId){
        User user = userRepo.findById(userId)
                    .orElseThrow(()->new RuntimeException("User not found."));

        if(user != null){
            UserResponse userResponse = mapToUserResponse(user);
            return userResponse;

        }
        
        return null;
    }

    public UserResponse registerUser(UserResponse userResponse){
        Optional<User> existingUser = userRepo.findById(userResponse.getUserId());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User savedUser = new User();
        savedUser.setUserId(userResponse.getUserId());
        savedUser.setUsername(userResponse.getUsername());
        savedUser.setFirstName(userResponse.getFirstName());
        savedUser.setLastName(userResponse.getLastName());
        savedUser.setEmail(userResponse.getEmail());
        savedUser.setProfilePicUrl(userResponse.getProfilePicUrl());
        return mapToUserResponse(savedUser);
    }

    public UserResponse mapToUserResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getUserId());
        userResponse.setUsername(user.getUsername());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setProfilePicUrl(user.getProfilePicUrl());
        return userResponse;
    }
}
