package com.example.apiexample.controller;

import com.example.apiexample.model.RequestApi;

import java.util.ArrayList;
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
        String url = "https://2.intelx.io/intelligent/search";
        String token = "87b9fdc9-9c85-414d-8810-4263fb0b0968";
        List<String> myList = new ArrayList<>();
        String jsonInString = "";
        
        ObjectMapper mapper = new ObjectMapper();

        RequestApi requestApi = RequestApi.builder()
        .term("gpinto@upfa.br")
        .lookuplevel(0)
        .maxresults(1000)
        .timeout(0)
        .datefrom("")
        .dateto("")
        .sort(2)
        .media(0)
        .terminate(myList)
        .build();

        try {
            jsonInString = mapper.writeValueAsString(requestApi);

        } catch (Exception e) {
            System.out.println(e);
            return "erro convert";
        }

        ResponseEntity<Object> result = MakeRequest(url,jsonInString,token);
            
        return result.getBody().toString();
        
    }

    ResponseEntity<Object> MakeRequest(String url,String jsonInString, String token) {
        
        RestTemplate restTemplat = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("x-key", token);

        HttpEntity<String> requestEnty = new HttpEntity<>(jsonInString, headers);

        ResponseEntity<Object> result = restTemplat.postForEntity(url, requestEnty, Object.class);
          
        return result;
    }
        
}
