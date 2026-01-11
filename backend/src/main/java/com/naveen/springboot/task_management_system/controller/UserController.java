package com.naveen.springboot.task_management_system.controller;

import com.naveen.springboot.task_management_system.dto.request.UserRequestDto;
import com.naveen.springboot.task_management_system.dto.response.UserResponseDto;
import com.naveen.springboot.task_management_system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable Integer id) {
        return userService.retrieveUser(id);
    }
}
