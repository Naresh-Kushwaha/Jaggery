package com.naresh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String houseNo;
    private String street;
    private String landmark;
    private String district;
    private String state;
    private String country;
    private String pinCode;
    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private UserEntity userEntity;

}
