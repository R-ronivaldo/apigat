package com.example.apiexample.controller;

import java.util.LinkedHashMap;

import com.example.apiexample.services.ApiService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    ApiService externalApiController = new ApiService();

    @PostMapping("/Search")
    public LinkedHashMap<String, Object> index(){
        return externalApiController.requestSearch("juniorferreira59@outlook.com");
    }
}
