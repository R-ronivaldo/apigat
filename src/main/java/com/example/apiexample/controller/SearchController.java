package com.example.apiexample.controller;

import java.util.List;
import java.util.Optional;

import com.example.apiexample.model.Search;
import com.example.apiexample.respository.SearchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {
    private final SearchRepository searchRepository;

    @Autowired
    public SearchController(SearchRepository searchRepository){
        this.searchRepository = searchRepository;
    }

    @GetMapping("/search")
    public List<Search> getAllAtivos(){
        return searchRepository.findAll();
    }

    @GetMapping("/search/{id}")
    public Search getAtivo(Long id){
        Optional<Search> searchFind = this.searchRepository.findById(id);

        if (searchFind.isPresent()){
            return searchFind.get();
        }

        return null;
    }

    @PostMapping("/search")
    public Search addAtivo(Search search){
        return this.searchRepository.save(search);
    }
}
