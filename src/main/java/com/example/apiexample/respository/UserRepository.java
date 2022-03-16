package com.example.apiexample.respository;

import java.util.List;

import com.example.apiexample.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmail(String email);
}

