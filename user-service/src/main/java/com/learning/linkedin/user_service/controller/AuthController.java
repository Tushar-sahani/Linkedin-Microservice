package com.learning.linkedin.user_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.linkedin.user_service.dto.LoginRequestDto;
import com.learning.linkedin.user_service.dto.SignupRequestDto;
import com.learning.linkedin.user_service.dto.UserDto;
import com.learning.linkedin.user_service.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupRequestDto signupRequestDto) {
        UserDto userDto = authService.signup(signupRequestDto);
        
        return new ResponseEntity<>(userDto,HttpStatus.CREATED);
    }

     @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto) throws BadRequestException {
        String token = authService.login(loginRequestDto);
        
        return ResponseEntity.ok(token);
    }
    
}
