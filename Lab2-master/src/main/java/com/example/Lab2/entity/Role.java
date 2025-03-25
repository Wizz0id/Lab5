package com.example.Lab2.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToMany(mappedBy = "rolesList")
    @ToString.Exclude
    private List<User> userList;

    @Override
    public String getAuthority() {
        return getName();
    }
    public Role(String name){
        this.name = name;
    }
}
