package com.sparta.schedulemanagementserver.controller;

import com.sparta.schedulemanagementserver.dto.AuthRequestDto;
import com.sparta.schedulemanagementserver.util.JwtTokenProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

public class AuthController {

    @PostMapping("/auth")
    public String createToken(@RequestBody AuthRequestDto requestDto) throws Exception {
        if ("user".equals(requestDto.getUsername()) && "password".equals(requestDto.getPassword())) {
            return JwtTokenProvider.generateToken(requestDto.getUsername());
        } else {
            throw new Exception("유효하지 않은 자격증명");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestHeader("Authorization") String token) {
        String username = JwtTokenProvider.extractUsername(token.substring(7));
        if (JwtTokenProvider.validateToken(token.substring(7), username)) {
            return "유효한 토큰";
        } else {
            return "유효하지 않은 토큰";
        }
    }
}
