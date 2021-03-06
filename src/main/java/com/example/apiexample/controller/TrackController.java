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

    public List<Track> getAllTrack(){
        return trackRepository.findAll();
    }

    @GetMapping("/track/{id}")
    public Track getTrack(@PathVariable("id") Long id){
        Optional<Track> trackFind = this.trackRepository.findById(id);

        if (trackFind.isPresent()){
            return trackFind.get();
        }

        return null;
    }

    @GetMapping("/track/ativos/{id}")
    public List<Track> getAllTrackByAtivo_id(@PathVariable("id") Long id){
        return trackRepository.findByAtivo_id(id);
    }

    public Track addTrack(@RequestBody Track track){
        return this.trackRepository.save(track);
    }
}
