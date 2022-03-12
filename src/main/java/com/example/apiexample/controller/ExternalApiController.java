package com.example.apiexample.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import com.example.apiexample.model.ResquestModel;
import com.example.apiexample.model.external.ResquestExternalApiModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class ExternalApiController {

    String urlBased = "https://2.intelx.io";

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @PostMapping("/Search")
    public LinkedHashMap<String, Object> requestSearch(@RequestBody ResquestModel requestModel){
        LinkedHashMap<String, Object> result = this.getSearch(requestModel.getEmail());
        System.out.println(result);
        return result;
    }


    private LinkedHashMap<String, Object> getSearch(String string) {
        String url = urlBased + "/intelligent/search";
        String token = "3e301f8b-3604-499f-a8c7-cd273220a882";
        List<String> TerminateList = new ArrayList<>();
        String jsonInString = "";
        LinkedHashMap<String, Object> resultList = new LinkedHashMap<String, Object>();

        ResquestExternalApiModel requestApi = ResquestExternalApiModel.builder()
        .term(string)
        .lookuplevel(0)
        .maxresults(1000)
        .timeout(0)
        .datefrom("")
        .dateto("")
        .sort(2)
        .media(0)
        .terminate(TerminateList)
        .build();

        jsonInString = convertRequestToJson(requestApi);

        ResponseEntity<Object> result = MakeRequest(url,jsonInString,token);

        resultList = (LinkedHashMap<String, Object>) result.getBody();
        System.out.println(result.getBody().getClass().getName());
        return getResult(resultList.get("id").toString(), token);
        //return "olá";
    }

    private ResponseEntity<Object> MakeRequest(String url, String jsonBody, String token) {
        
        RestTemplate restTemplat = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("x-key", token);

        HttpEntity<String> requestEnty = new HttpEntity<>(jsonBody, headers);

        ResponseEntity<Object> result = restTemplat.postForEntity(url, requestEnty, Object.class);

        System.out.println(result.getBody());
          
        return result;
    }

    private String convertRequestToJson(Object object){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            System.out.println(e);
            return "error to convert";
        }
    }

    private LinkedHashMap<String, Object> getResult(String idSearch, String token){
        String url = urlBased + "/intelligent/search/result?id=" + idSearch;
        LinkedHashMap<String, Object> resultList = new LinkedHashMap<String, Object>();

        RestTemplate restTemplat = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("x-key", token);

        HttpEntity<String> requestEnty = new HttpEntity<>(headers);

        ResponseEntity<Object> result = restTemplat.exchange(url, HttpMethod.GET, requestEnty, Object.class);

        resultList = (LinkedHashMap<String, Object>) result.getBody();
        return resultList;
    }
        
}
