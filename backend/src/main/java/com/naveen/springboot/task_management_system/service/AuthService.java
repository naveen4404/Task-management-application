package com.naveen.springboot.task_management_system.service;


import com.naveen.springboot.task_management_system.dto.mapper.UserMapper;
import com.naveen.springboot.task_management_system.dto.request.UserRequestDto;
import com.naveen.springboot.task_management_system.dto.response.JwtResponse;
import com.naveen.springboot.task_management_system.dto.response.UserResponseDto;
import com.naveen.springboot.task_management_system.entity.User;
import com.naveen.springboot.task_management_system.exception.BadRequestException;
import com.naveen.springboot.task_management_system.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private UserRepository userRepository;
    private PasswordEncoder encoder;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder encoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public JwtResponse handleLogIn(UserRequestDto userRequestDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequestDto.getEmail(),userRequestDto.getPassword())
        );

        if(!authentication.isAuthenticated()){
            throw  new BadRequestException("Invalid Email or Password. Verify!");
        }

        String token = jwtService.getJwtToken(userRequestDto.getEmail());
        return new JwtResponse(userRequestDto.getEmail(),token);
    }

    public UserResponseDto createUser(UserRequestDto userRequestDto){
        User user = UserMapper.toEntity(userRequestDto);
        user.setPassword(encoder.encode(user.getPassword()));
        User createdUser = userRepository.save(user);
        return  UserMapper.toResponse(createdUser);
    }
}
