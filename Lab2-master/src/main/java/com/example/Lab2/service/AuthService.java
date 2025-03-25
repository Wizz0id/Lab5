package com.example.Lab2.service;

import com.example.Lab2.dto.UserDTO;
import com.example.Lab2.entity.Role;
import com.example.Lab2.entity.User;
import com.example.Lab2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean saveUser(UserDTO user)
    {
        User finded = userRepository.findByUsername(user.getUsername());
        if(finded != null) return false;
        User new_user = new User();
        new_user.setUsername(user.getUsername());
        new_user.setRolesList(userRepository.findRoleByName("ROLE_USER"));
        new_user.setPassword(passwordEncoder.encode(user.getPassword()));
        new_user.setExpired(false);
        userRepository.save(new_user);
        return true;
    }
}
