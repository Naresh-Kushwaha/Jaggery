package com.naresh.mapper;

import com.naresh.dto.AddressDTO;
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
            for (AddressDTO addrReq : req.address()) {
                Address address = new Address();
                address.setHouseNo(addrReq.houseNo());
                address.setStreet(addrReq.street());
                address.setLandmark(addrReq.landmark());
                address.setDistrict(addrReq.district());
                address.setState(addrReq.state());
                address.setCountry(addrReq.country());
                address.setPinCode(addrReq.pinCode());
                // Do NOT set userEntity here. Set it in service after user is built.
                addressList.add(address);
            }
        }

        return Customer.builder()
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
