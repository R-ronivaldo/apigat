package com.example.apiexample.respository;

import java.util.List;

import com.example.apiexample.model.Search;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchRepository extends JpaRepository<Search, Long> {
    public List<Search> findByTrack_id( Long user_id);
}
