package com.example.Lab2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private long id;
    private String username;
    private String password;
    private Boolean expired;
    public UserDTO(String username, String password){
        this.username = username;
        this.password = password;
        expired = false;
    }
}
