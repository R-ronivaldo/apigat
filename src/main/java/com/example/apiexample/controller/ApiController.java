package com.example.apiexample.controller;

import java.util.LinkedHashMap;

import com.example.apiexample.model.Ativo;
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

    @PostMapping("/index")
    public LinkedHashMap<String, Object> index(@RequestBody Ativo ativo){
        Ativo ativoCreated = ativoController.addAtivo(ativo);
        Track trackFind = trackController.getAtivo(ativoCreated.getId());
        if(trackFind != null){
            System.out.println(trackFind);
            System.out.println("encontrado!!!!!");
        } else {
            Track track = new Track();
            track.setAtivo_id(ativoCreated.getId());
            Track trackCreated = trackController.addAtivo(track);
            System.out.println(trackCreated);
            System.out.println("criado!!!!!");
        }
        
        LinkedHashMap<String, Object> externalApiResult = externalApiController.requestSearch(ativo.getEmail());

        return externalApiResult;
    
    }
}
