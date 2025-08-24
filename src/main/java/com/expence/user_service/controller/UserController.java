package com.expence.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expence.user_service.dto.UserResponse;
import com.expence.user_service.service.UserService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}/validate")
    public ResponseEntity<Boolean> validateUser(@PathVariable String userId) {
        return ResponseEntity.ok(userService.exitsByUserId(userId));
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String userId) {
        log.info("hi how are you");
        return ResponseEntity.ok(userService.getUserById(userId));
    }
    
    @PostMapping("/register")
    public ResponseEntity<UserResponse> postMethodName(@RequestBody UserResponse userResponse) {
        log.info("hi registering please wait");
        return ResponseEntity.ok(userService.registerUser(userResponse));
    }
    
}
