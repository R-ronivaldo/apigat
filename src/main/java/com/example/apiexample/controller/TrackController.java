package com.example.apiexample.controller;

import java.util.List;
import java.util.Optional;

import com.example.apiexample.model.Track;
import com.example.apiexample.respository.TrackRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrackController {
    private final TrackRepository trackRepository;

    @Autowired
    public TrackController(TrackRepository trackRepository){
        this.trackRepository = trackRepository;
    }

    @GetMapping("/track")
    public List<Track> getAllAtivos(){
        return trackRepository.findAll();
    }

    @GetMapping("/track/{id}")
    public Track getAtivo(@PathVariable("id") Long id){
        Optional<Track> trackFind = this.trackRepository.findById(id);

        if (trackFind.isPresent()){
            return trackFind.get();
        }

        return null;
    }

    @PostMapping("/track")
    public Track addAtivo(@RequestBody Track track){
        return this.trackRepository.save(track);
    }
}
