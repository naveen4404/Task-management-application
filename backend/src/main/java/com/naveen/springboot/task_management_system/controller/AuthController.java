package com.naveen.springboot.task_management_system.controller;


import com.naveen.springboot.task_management_system.dto.mapper.UserMapper;
import com.naveen.springboot.task_management_system.dto.request.UserRequestDto;
import com.naveen.springboot.task_management_system.dto.response.JwtResponse;
import com.naveen.springboot.task_management_system.dto.response.UserResponseDto;
import com.naveen.springboot.task_management_system.entity.User;
import com.naveen.springboot.task_management_system.exception.BadRequestException;
import com.naveen.springboot.task_management_system.repository.UserRepository;
import com.naveen.springboot.task_management_system.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private AuthService authService;
    private UserRepository userRepository;
    public AuthController(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(authService.handleLogIn(userRequestDto));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto createdUser = authService.createUser(userRequestDto);
        // URI location = ServletUriComponentsBuilder
        // .fromCurrentRequest()
        // .path("/{id}")
        // .buildAndExpand(createdUser.getId()).toUri();
        return ResponseEntity.created(null).body(createdUser);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> validateToken(Authentication authentication) {
        if(authentication==null){
            throw new BadRequestException("Invalid authentication. Login again.");
        }
        Optional<User> user = userRepository.findUserByEmail(authentication.getName());
        if (user.isEmpty()){
            throw new BadRequestException("User not found with this email: "+authentication.getName());
        }

        return ResponseEntity.ok(UserMapper.toResponse(user.get()));
    }

}

