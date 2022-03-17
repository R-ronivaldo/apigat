package com.example.apiexample.controller;

import java.util.List;
import java.util.Optional;

import com.example.apiexample.model.Search;
import com.example.apiexample.respository.SearchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {
    private final SearchRepository searchRepository;

    @Autowired
    public SearchController(SearchRepository searchRepository){
        this.searchRepository = searchRepository;
    }

    public List<Search> getAllSearch(){
        return searchRepository.findAll();
    }

    @GetMapping("/search/{id}")
    public Search getSearch(@PathVariable("id") Long id){
        Optional<Search> searchFind = this.searchRepository.findById(id);

        if (searchFind.isPresent()){
            return searchFind.get();
        }

        return null;
    }

    @GetMapping("/search/track/{id}")
    public List<Search> getAllSearchByTrack(@PathVariable("id") Long id){
        return searchRepository.findByTrack_id(id);
    }

    public Search addSearch(Search search){
        return this.searchRepository.save(search);
    }
}
