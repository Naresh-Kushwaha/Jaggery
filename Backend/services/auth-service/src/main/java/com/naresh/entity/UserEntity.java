package com.naresh.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.util.List;

@Entity
@Data
public class UserEntity {
    @Id
    private String email;
    private String name;
    private String password;
    private String mobile;
    @Enumerated(EnumType.STRING)
    private List<Roles> roles;
}
