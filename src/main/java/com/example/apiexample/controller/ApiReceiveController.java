package com.example.apiexample.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ApiReceiveController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/Search")
    private String getCountries() {
        
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplat = new RestTemplate();

        String token = "87b9fdc9-9c85-414d-8810-4263fb0b0968";
        String url = "https://2.intelx.io/";

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("x-key", token);

        Map<String, String> bodyParamMap = new HashMap<>();

        bodyParamMap.put("term", "gpinto@upfa.br");
        bodyParamMap.put("lookuplevel", "0");
        bodyParamMap.put("maxresults", "1000");
        bodyParamMap.put("timeout", "0");
        bodyParamMap.put("datefrom", "");
        bodyParamMap.put("dateto", "");
        bodyParamMap.put("sort", "2");
        bodyParamMap.put("media", "0");
        bodyParamMap.put("terminate", "[]");
        try {

            String reqBodyData = new ObjectMapper().writeValueAsString(bodyParamMap);
            
            HttpEntity<String> requestEnty = new HttpEntity<>(reqBodyData, headers);

            ResponseEntity<Object> result = restTemplat.postForEntity(url, requestEnty, Object.class);
        
            return result.toString();
            
        } catch (Exception e) {
            System.out.println("erro aqui");
            return "erro";
        }
        
    }
        
}
