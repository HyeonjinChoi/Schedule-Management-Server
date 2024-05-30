package com.sparta.schedulemanagementserver.dto;

import lombok.Getter;

@Getter
public class TodoRequestDto {
    private String title;
    private String username;
    private String contents;
    private String password;
}
