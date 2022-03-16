package com.example.apiexample.controller;

import java.util.List;

import com.example.apiexample.model.Ativo;
import com.example.apiexample.model.Search;
import com.example.apiexample.model.Track;
import com.example.apiexample.services.ExternalApiService;

import org.springframework.beans.factory.annotation.Autowired;
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
        Track track = new Track();
        Ativo ativoDB = new Ativo();
        Track trackDB = new Track();

        List<Search> externalApiResult = externalApiController.requestSearch(ativo.getEmail());

        //TODO - check if ativo exist

        ativo.setTrack(track);

        track.setAtivo(ativo);
            
        ativoDB = ativoController.addAtivo(ativo);

        trackDB = trackController.getTrack(ativoDB.getTrack().getId());


        for (int i = 0; i < externalApiResult.size(); i++) {
                
            Search item = externalApiResult.get(i);
                        
            item.setTrack(trackDB);
                        
            searchController.addSearch(item);
        }

        return externalApiController.requestSearch(ativo.getEmail());
    
    }
}