package com.naresh.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.util.List;

@Entity
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private List<Roles> roles;
}
