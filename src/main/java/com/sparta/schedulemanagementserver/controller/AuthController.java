package com.sparta.schedulemanagementserver.controller;

import com.sparta.schedulemanagementserver.dto.AuthRequestDto;
import com.sparta.schedulemanagementserver.dto.AuthResponseDto;
import com.sparta.schedulemanagementserver.dto.RegisterRequestDto;
import com.sparta.schedulemanagementserver.dto.RegisterResponseDto;
import com.sparta.schedulemanagementserver.entity.User;
import com.sparta.schedulemanagementserver.service.UserService;
import com.sparta.schedulemanagementserver.util.JwtTokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

    private final UserService userService;

    @PostMapping("/authenticate")
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

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto requestDto) {
        try {
            userService.register(requestDto);
            RegisterResponseDto registerResponse = new RegisterResponseDto("유저 등록 성공");
            return new ResponseEntity<>(registerResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new RegisterResponseDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto requestDto) {
        try {
            User user = userService.login(requestDto.getUsername(), requestDto.getPassword());
            String token = JwtTokenProvider.generateToken(user.getUsername());
            AuthResponseDto authResponse = new AuthResponseDto("로그인 성공", token);
            return new ResponseEntity<>(authResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new AuthResponseDto("유효하지 않은 사용자 이름 혹은 비밀번호", null), HttpStatus.UNAUTHORIZED);
        }
    }
}
