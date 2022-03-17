package com.example.apiexample.respository;

import java.util.List;

import com.example.apiexample.model.Track;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Long> {
    public List<Track> findByAtivo_id( Long user_id);
}
