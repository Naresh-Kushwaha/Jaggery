package com.naresh.mapper;

import com.naresh.dto.UserEntityRequest;
import com.naresh.dto.UserEntityResponse;
import com.naresh.model.Address;
import com.naresh.model.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    public UserEntity toUserEntity(UserEntityRequest req) {
        if (req == null) return null;

        List<Address> addressList = new ArrayList<>();

        if (req.address() != null) {
            for (Address addrReq : req.address()) {
                Address address = new Address();
                address.setHouseNo(addrReq.getHouseNo());
                address.setStreet(addrReq.getStreet());
                address.setLandmark(addrReq.getLandmark());
                address.setDistrict(addrReq.getDistrict());
                address.setState(addrReq.getState());
                address.setCountry(addrReq.getCountry());
                address.setPinCode(addrReq.getPinCode());
                // Do NOT set userEntity here. Set it in service after user is built.
                addressList.add(address);
            }
        }

        return UserEntity.builder()
                .username(req.username())
                .password(req.password())
                .mobile(req.mobile())
                .email(req.email())
                .address(addressList)
                .build();
    }

    public UserEntityResponse fromUserEntity(UserEntity userEntity){
        if(userEntity==null)return null;
        return new UserEntityResponse(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getEmail()
        );
    }
}
