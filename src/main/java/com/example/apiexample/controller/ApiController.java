package com.example.apiexample.controller;

import java.util.List;
import java.util.Optional;

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

        ResponseEntity<List<Ativo>> ativoFind = ativoController.getAtivosByEmail(ativo.getEmail());

        Ativo ativoCreatedOrFind = new Ativo();

        System.out.println(ativoFind.getBody().isEmpty());

        if (ativoFind.getBody().isEmpty()){
            
            ativoCreatedOrFind = ativoController.addAtivo(ativo);
            System.out.println("criado e foi pro banco");

        } else {
            ativoCreatedOrFind = ativoFind.getBody().get(0);
            System.out.println("veio do banco");
        }

        System.out.println(ativoCreatedOrFind);
        
        Track trackFind = trackController.getTrack(ativoCreatedOrFind.getId());
        
        if(trackFind != null){

            for (int i = 0; i < externalApiResult.size(); i++) {
                
                Search item = externalApiResult.get(i);
                
                item.setTrack_id(trackFind.getId());
                
                searchController.addSearch(item);
                
            }
        
        } else {

            Track track = new Track();
            
            track.setAtivo_id(ativoCreatedOrFind.getId());
            
            Track trackCreated = trackController.addTrack(track);

            for (int i = 0; i < externalApiResult.size(); i++) {
                
                Search item = externalApiResult.get(i);
                    
                item.setTrack_id(trackCreated.getId());
                    
                searchController.addSearch(item);
                    
            }
        
        }

        return externalApiResult;
    
    }
}