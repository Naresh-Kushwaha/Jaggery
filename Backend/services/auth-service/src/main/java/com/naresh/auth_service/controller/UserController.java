package com.naresh.auth_service.controller;

import com.naresh.auth_service.model.LoginRequest;
import com.naresh.auth_service.model.UserEntity;

import com.naresh.auth_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserEntity userEntity){
        return userService.register(userEntity);
    }
    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request){
        return userService.login(request);
    }
    @GetMapping("/validate")
    public ResponseEntity<Map<String,Object>> validate( @RequestParam("token") String token){
        return ResponseEntity.ok(userService.validate(token));
    }

}
