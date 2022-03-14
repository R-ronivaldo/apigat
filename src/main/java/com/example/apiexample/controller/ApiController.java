package com.example.apiexample.controller;

import java.util.LinkedHashMap;
import java.util.List;

import com.example.apiexample.model.Ativo;
import com.example.apiexample.model.Search;
import com.example.apiexample.model.Track;
import com.example.apiexample.model.external.ResponseExternalApi;
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
    public List<ResponseExternalApi> index(@RequestBody Ativo ativo){
        
        Ativo ativoCreated = ativoController.addAtivo(ativo);
        
        Track trackFind = trackController.getTrack(ativoCreated.getId());
        
        if(trackFind != null){

            Search search = new Search();

            search.setTrack_id(trackFind.getId());

            Search searchFind = searchController.addSearch(search);

            System.out.println(searchFind);

            //TODO return search
        
        } else {

            Track track = new Track();
            
            track.setAtivo_id(ativoCreated.getId());
            
            Track trackCreated = trackController.addTrack(track);

            if (trackCreated != null){

                Search search = new Search();

                search.setTrack_id(trackCreated.getId());

                Search searchFind = searchController.addSearch(search);

                System.out.println(searchFind);

                //TODO return search

            }
        
        }
        
        List<ResponseExternalApi> externalApiResult = externalApiController.requestSearch(ativo.getEmail());

        return externalApiResult;
    
    }
}