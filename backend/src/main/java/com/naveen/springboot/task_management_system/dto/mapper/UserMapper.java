package com.naveen.springboot.task_management_system.dto.mapper;

import com.naveen.springboot.task_management_system.dto.request.UserRequestDto;
import com.naveen.springboot.task_management_system.dto.response.UserResponseDto;
import com.naveen.springboot.task_management_system.entity.User;

public class UserMapper {

    public static User toEntity(UserRequestDto userRequestDto){
        User user = new User();
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        return user;
    }

    public static UserResponseDto toResponse(User user){

        return new UserResponseDto(user.getId(), user.getEmail());

    }

}
