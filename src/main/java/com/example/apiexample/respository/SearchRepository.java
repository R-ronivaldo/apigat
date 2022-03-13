package com.example.apiexample.respository;

import com.example.apiexample.model.Search;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchRepository extends JpaRepository<Search, Long> {
    
}
