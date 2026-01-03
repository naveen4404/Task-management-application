package com.naveen.springboot.task_management_system.service;

import com.naveen.springboot.task_management_system.dto.mapper.UserMapper;
import com.naveen.springboot.task_management_system.dto.request.UserRequestDto;
import com.naveen.springboot.task_management_system.dto.response.UserResponseDto;
import com.naveen.springboot.task_management_system.entity.User;
import com.naveen.springboot.task_management_system.exception.BadRequestException;
import com.naveen.springboot.task_management_system.exception.ResourceNotFoundException;
import com.naveen.springboot.task_management_system.exception.UnAuthorizedException;
import com.naveen.springboot.task_management_system.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    private  UserRepository userRepository;

    private PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public UserResponseDto retrieveUser(Integer id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new ResourceNotFoundException("User with the Id: "+id+" is not found");
        }

        return UserMapper.toResponse(user.get());
    }

    public List<UserResponseDto> retrieveAllUsers(){
        List<User> users = userRepository.findAll();
        List<UserResponseDto> resultSet = users.stream().map(user -> UserMapper.toResponse(user)).toList();
        return  resultSet;
    }

    public UserResponseDto createUser(UserRequestDto userRequestDto){

        User user = UserMapper.toEntity(userRequestDto);
        user.setPassword(encoder.encode(user.getPassword()));
        User createdUser = userRepository.save(user);
        return  UserMapper.toResponse(createdUser);
    }

    public UserResponseDto handleLogIn(String token){

        if(token==null || !token.startsWith("Basic ")){
            throw new BadRequestException("Missing token in the header");
        }

        // 1) Remove Basic
        String encodedCredentials = token.substring(6);
        if(encodedCredentials.isBlank()){
            throw new BadRequestException("Missing token in the header");
        }

        // decode Base64
        byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);
        String decoded = new String(decodedBytes, StandardCharsets.UTF_8);

        // split user email & password
        String[] credentials = decoded.split(":",2);
        String userEmail = credentials[0];
        String password = credentials[1];

        // validate
        Optional<User> user = userRepository.findUserByEmail(userEmail);
        if(user.isEmpty()){
            throw new ResourceNotFoundException("User is not found");
        }
        String hashedPass = user.get().getPassword();
        boolean isValid = encoder.matches(password,hashedPass);
        if(!isValid){
            throw new UnAuthorizedException("Wrong credentials. Check the credentials!");
        }
        return UserMapper.toResponse(user.get());
    }


}
