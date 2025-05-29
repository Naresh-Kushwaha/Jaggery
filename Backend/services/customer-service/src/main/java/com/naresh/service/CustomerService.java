package com.naresh.service;

import com.naresh.dto.AddressDTO;
import com.naresh.dto.CustomerRequest;
import com.naresh.dto.CustomerResponse;
import com.naresh.exception.AddressNotFoundException;
import com.naresh.exception.CustomerNotFoundException;
import com.naresh.mapper.AddressMapper;
import com.naresh.mapper.CustomerMapper;
import com.naresh.model.Address;
import com.naresh.model.Customer;
import com.naresh.repository.AddressRepository;
import com.naresh.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final CustomerMapper usermapper;
    private final AddressMapper addressMapper;


    public CustomerResponse register(CustomerRequest customerRequest) {
        Customer user = usermapper.toUserEntity(customerRequest);
        for (Address addr : user.getAddress()) {
            addr.setCustomer(user);
        }
        Customer savedUser = customerRepository.save(user);
        return usermapper.fromUserEntity(savedUser);
    }
    public CustomerResponse findCustomer(Long id){
        Customer customer= customerRepository.findById(id).orElseThrow(()->
                new CustomerNotFoundException("Customer not Found With the give ID : "+id));
        return usermapper.fromUserEntity(customer);
    }
    public List<AddressDTO> getAddressByCustomerId(Long id){
        Customer user= customerRepository.findById(id).orElseThrow(()->
                new CustomerNotFoundException("Customer not found for the given ID : "+id));
        return
                user.getAddress().stream()
                        .map(addressMapper::toAddressDTO)
                        .toList();
    }

    public AddressDTO updateAddByAddId(Long id, AddressDTO addressDTO) {
        Address address=addressRepository.findById(id).orElseThrow(()->{
            return new CustomerNotFoundException("Address not found for Id: "+id);
        });

        setIfNotNull(addressDTO.country(), address::setCountry);
        setIfNotNull(addressDTO.district(), address::setDistrict);
        setIfNotNull(addressDTO.houseNo(),address::setHouseNo);
        setIfNotNull(addressDTO.landmark(),address::setLandmark);
        setIfNotNull(addressDTO.state(),address::setState);
        setIfNotNull(addressDTO.street(),address::setStreet);
        setIfNotNull(addressDTO.pinCode(),address::setPinCode);
        Address updatedAddress=addressRepository.save(address);
        return addressMapper.toAddressDTO(updatedAddress);

    }
    private <T> void setIfNotNull(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }

    public void deleteUser(Long id) {
        customerRepository.findById(id).ifPresentOrElse(
                customer -> customerRepository.deleteById(id),
                () -> { throw new CustomerNotFoundException("Customer not Found with the given Id: " + id); }
        );

    }

    public void deleteAddress(Long id) {

        addressRepository.findById(id).ifPresentOrElse(
                address -> addressRepository.deleteById(id),
                ()-> {
                    throw new AddressNotFoundException("Address  not Found with the given Id: " + id);
                }        );



    }
}