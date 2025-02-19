package com.example.practicesecurity.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class UserEntity {

    @Id // Entity 에 필수적
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true) // username 중복을 방지해주는 로직
    private String username;

    private String password;

    private String role;
}
