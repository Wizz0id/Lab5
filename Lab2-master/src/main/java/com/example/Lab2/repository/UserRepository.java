package com.example.Lab2.repository;

import com.example.Lab2.entity.Role;
import com.example.Lab2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select id, username, password, expired, role_id from users inner join " +
            "user_roles on users.id = user_roles.user_id where username=:username", nativeQuery = true)
    User findByUsername(@Param("username") String username);
    @Query("SELECT r FROM Role r WHERE r.name = :name")
    Set<Role> findRoleByName(@Param("name") String name);
}
