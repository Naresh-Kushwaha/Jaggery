package com.naresh.controller;

import com.naresh.dto.AddressDTO;
import com.naresh.dto.UserEntityRequest;
import com.naresh.dto.UserEntityResponse;
import com.naresh.model.UserEntity;
import com.naresh.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    @PostMapping("/register")
    public ResponseEntity<UserEntityResponse> register(@RequestBody UserEntityRequest user){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.register(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity>getUser(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getUser(id));
    }
    @GetMapping("/address/{id}")
    public ResponseEntity<List<AddressDTO>>getAddress(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAddress(id));
    }
}
