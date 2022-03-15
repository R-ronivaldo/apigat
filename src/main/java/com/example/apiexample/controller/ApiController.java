package com.example.apiexample.controller;

import java.util.List;

import com.example.apiexample.model.Ativo;
import com.example.apiexample.model.Search;
import com.example.apiexample.model.Track;
import com.example.apiexample.services.ExternalApiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class ApiController {
    ExternalApiService externalApiController = new ExternalApiService();
    
    @Autowired
    private AtivoController ativoController;

    @Autowired
    private TrackController trackController;

    @Autowired
    private SearchController searchController;

    @PostMapping("/index")
    public List<Search> index(@RequestBody Ativo ativo){

        List<Search> externalApiResult = externalApiController.requestSearch(ativo.getEmail());

        Ativo ativoCreated = new Ativo("junior@gmail.com", "web");

        Track track = new Track();

        ativoCreated.setTrack(track);

        track.setAtivo(ativoCreated);
        
        Ativo ativoDB = ativoController.addAtivo(ativoCreated);

        Track trackDB = trackController.getTrack(ativoDB.getTrack().getId());

        for (int i = 0; i < externalApiResult.size(); i++) {
                
            Search item = externalApiResult.get(i);
                
            item.setTrack(trackDB);
                
            searchController.addSearch(item);
        }

        return externalApiController.requestSearch(ativo.getEmail());
    
    }
}