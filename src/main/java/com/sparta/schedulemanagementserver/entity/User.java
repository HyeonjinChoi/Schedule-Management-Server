package com.sparta.schedulemanagementserver.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private String authority;

    public void setUserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
