package com.naveen.springboot.task_management_system.controller;


import com.naveen.springboot.task_management_system.dto.response.UserResponseDto;
import com.naveen.springboot.task_management_system.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/users")
    public List<UserResponseDto> retrieveAllUsers() {
        return userService.retrieveAllUsers();
    }
}
