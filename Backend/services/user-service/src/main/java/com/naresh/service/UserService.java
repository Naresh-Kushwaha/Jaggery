package com.naresh.service;

import com.naresh.dto.AddressDTO;
import com.naresh.dto.UserEntityRequest;
import com.naresh.dto.UserEntityResponse;
import com.naresh.mapper.AddressMapper;
import com.naresh.mapper.UserMapper;
import com.naresh.model.Address;
import com.naresh.model.UserEntity;
import com.naresh.repository.AddressRepository;
import com.naresh.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserEntityRepository userEntityRepository;
    private final AddressRepository addressRepository;
    private final UserMapper usermapper;
    private final AddressMapper addressMapper;

    public UserEntityResponse register(UserEntityRequest userEntityRequest) {
        UserEntity user = usermapper.toUserEntity(userEntityRequest);
        List<Address> add = user.getAddress();
        for (Address addr : user.getAddress()) {
            addr.setUserEntity(user);
        }
        UserEntity savedUser = userEntityRepository.save(user);
        return usermapper.fromUserEntity(savedUser);
    }

    public UserEntity getUser(Long id){
        return userEntityRepository.findById(id).get();
    }
    public List<AddressDTO> getAddress(Long id){
        UserEntity user= userEntityRepository.findById(id).get();
        return
                user.getAddress().stream()
                        .map(addressMapper::toAddressDTO)
                        .toList();

    }


}