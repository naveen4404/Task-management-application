package com.naveen.springboot.task_management_system.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class UserRequestDto {

    @Email(message = "Please enter a valid email!")
    private String email;

    @Size(min = 5,message = "password must have at least 5 characters!")
    private String password;

    public UserRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
