package com.naveen.springboot.task_management_system.service;

import com.naveen.springboot.task_management_system.dto.mapper.UserMapper;
import com.naveen.springboot.task_management_system.dto.request.UserRequestDto;
import com.naveen.springboot.task_management_system.dto.response.JwtResponse;
import com.naveen.springboot.task_management_system.dto.response.UserResponseDto;
import com.naveen.springboot.task_management_system.entity.User;
import com.naveen.springboot.task_management_system.exception.ResourceNotFoundException;
import com.naveen.springboot.task_management_system.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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



}
