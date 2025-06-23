package com.naresh.auth_service.service;



import com.naresh.Jwt_Module.service.JwtService;
import com.naresh.auth_service.model.LoginRequest;
import com.naresh.auth_service.model.UserEntity;
import com.naresh.auth_service.repository.UserRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final JwtService jwtService;
    private final UserRepo userRepo;

    public ResponseEntity<String> register(UserEntity userEntity){
        UserEntity user=new UserEntity();
        user.setUsername(userEntity.getUsername());
        user.setPassword(userEntity.getPassword());
        user.setRoles(userEntity.getRoles());
        userRepo.save(user);
        return new ResponseEntity<>("User Registered Successfully", HttpStatus.OK);
    }
    public ResponseEntity<String> login(LoginRequest loginRequest){
        Optional<UserEntity> user=userRepo.findByUsername(loginRequest.getUsername());
        if(user.isPresent()) {
            UserEntity userFound = user.get();
            if (userFound.getPassword().equals(loginRequest.getPassword())) {
                return new ResponseEntity<>(jwtService.generateToken(userFound.getUsername(),userFound.getRoles()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Password is Incorrect", HttpStatus.UNAUTHORIZED);

            }
        }
          else {
              return new ResponseEntity<>("User not Found",HttpStatus.NOT_FOUND);
            }

    }
    public ResponseEntity<String> greet(){
        return new ResponseEntity<>("Hii,Good to see you",HttpStatus.OK);
    }

    public Map<String,Object>validate(String token){
        return jwtService.validateAndExtractClaims(token);
    }
}
