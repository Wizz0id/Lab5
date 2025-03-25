package com.example.Lab2.controller;

import com.example.Lab2.dto.UserDTO;
import com.example.Lab2.entity.User;
import com.example.Lab2.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegController {
    @Autowired
    public AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<Boolean> registration(@RequestParam String username, @RequestParam String password)
    {
        UserDTO userDto = new UserDTO(username, password);
        return ResponseEntity.ok(authService.saveUser(userDto));
    }
}
