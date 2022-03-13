package com.example.apiexample.controller;

import java.util.LinkedHashMap;

import com.example.apiexample.model.AtivoModel;
import com.example.apiexample.services.ExternalApiService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    ExternalApiService externalApiController = new ExternalApiService();

    @PostMapping("/Search")
    public LinkedHashMap<String, Object> index(@RequestBody AtivoModel ativo){
        return externalApiController.requestSearch(ativo.getEmail());
    }
}
