package com.learning.linkedin.user_service.service;

import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.learning.linkedin.user_service.dto.LoginRequestDto;
import com.learning.linkedin.user_service.dto.SignupRequestDto;
import com.learning.linkedin.user_service.dto.UserDto;
import com.learning.linkedin.user_service.entity.User;
import com.learning.linkedin.user_service.exception.ResourceNotFoundException;
import com.learning.linkedin.user_service.repository.UserRepository;
import com.learning.linkedin.user_service.utils.PasswordUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    public UserDto signup(SignupRequestDto signupRequestDto){
        User user = modelMapper.map(signupRequestDto, User.class);
        user.setPassword(PasswordUtil.hashPassword(signupRequestDto.getPassword()));

        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser, UserDto.class);
    }

    public String login(LoginRequestDto loginRequestDto) throws BadRequestException {
        User user = userRepository.findByEmail(loginRequestDto.getEmail())
            .orElseThrow(()-> new ResourceNotFoundException("User not found with email "+ loginRequestDto.getEmail()));

        boolean isPasswordmatch = PasswordUtil.checkPassword( loginRequestDto.getPassword(),user.getPassword());

        if(!isPasswordmatch){
            throw new BadRequestException("Incorrect Password");
        }

        return jwtService.generateAccessToken(user);
    }

}
