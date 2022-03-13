package com.example.apiexample.respository;

import com.example.apiexample.model.Track;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Long> {
    
}
