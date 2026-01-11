package com.naveen.springboot.task_management_system.service;


import com.naveen.springboot.task_management_system.entity.User;
import com.naveen.springboot.task_management_system.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TodoUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public TodoUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findUserByEmail(userName);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("User with the email: "+userName+" not found");
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.get().getEmail())
                .password(user.get().getPassword())
                .build();
    }
}
