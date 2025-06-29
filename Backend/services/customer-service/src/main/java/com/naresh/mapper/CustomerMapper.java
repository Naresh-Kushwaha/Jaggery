package com.naresh.mapper;

import com.naresh.dto.CustomerRequest;
import com.naresh.dto.CustomerResponse;
import com.naresh.model.Address;
import com.naresh.model.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerMapper {

    public Customer toUserEntity(CustomerRequest req) {
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

        return Customer.builder()
                .id(req.id())
                .mobile(req.mobile())
                .email(req.email())
                .address(addressList)
                .build();
    }

    public CustomerResponse fromUserEntity(Customer customer){
        if(customer ==null)return null;
        return new CustomerResponse(
                customer.getMobile(), customer.getEmail(), customer.getAddress()
        );
    }
}
