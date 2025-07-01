package com.naresh.controller;

import com.naresh.dto.AddressDTO;
import com.naresh.dto.CustomerRequest;
import com.naresh.dto.CustomerResponse;
import com.naresh.dto.LoginRequest;
import com.naresh.model.Customer;
import com.naresh.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;
    @GetMapping("/me")
    public ResponseEntity<?>JwtDecode(@AuthenticationPrincipal Jwt jwt){

        return ResponseEntity.status(HttpStatus.OK).body(jwt.getClaims());
    }
    @PostMapping("/saveCustomerDetails")
    public ResponseEntity<CustomerResponse> register(@RequestBody @Valid CustomerRequest customer){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.saveCustomerDetails(customer));
    }
    @GetMapping("/address")
    public ResponseEntity<List<AddressDTO>>getAddressByCustomerId(@RequestParam("userId") String id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAddressByCustomerId(id));
    }
    @GetMapping("/getCustomer/{id}")
    public ResponseEntity<CustomerResponse>getCustomer(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findCustomer(id));
    }

    @PutMapping("/update/address")
    public ResponseEntity<AddressDTO>updateAddByAddId(@RequestParam("id") Long id,@Valid  @RequestBody AddressDTO addressDTO){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.updateAddByAddId(id,addressDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id){
        service.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/address/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id){
        service.deleteAddress(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
