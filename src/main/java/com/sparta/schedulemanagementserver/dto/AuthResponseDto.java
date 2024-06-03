package com.sparta.schedulemanagementserver.dto;

public class AuthResponseDto {
    private String message;
    private String token;

    public AuthResponseDto(String message, String token) {
        this.message = message;
        this.token = token;
    }
}
