package com.example.apiexample.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiReceiveController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
        
}
