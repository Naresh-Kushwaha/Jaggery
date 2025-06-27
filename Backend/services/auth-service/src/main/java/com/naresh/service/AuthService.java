package com.naresh.service;


import com.naresh.dto.AuthRequest;
import com.naresh.entity.Roles;
import com.naresh.entity.UserEntity;
import com.naresh.repository.UserEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserEntityRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String saveUser(UserEntity userEntity){
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        repository.save(userEntity);
        return "User added to the system";
    }
    public String generateToken(String username){
        UserEntity userEntity=repository.findByUsername(username).orElseThrow(()->{
            throw new RuntimeException("user not found");
        });
        return jwtService.generateToken(username,userEntity.getRoles());

    }
    public  void validateToken(String token){
        jwtService.validateToken(token);
    }
    public Map<String,Object> validateAndExtractClaims(String token){
      return   jwtService.validateAndExtractClaims(token);
    }
}
