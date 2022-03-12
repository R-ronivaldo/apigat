package com.example.apiexample.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseApi {
    private String id;
    private boolean softselectorwarning;
    private int status;
    private String altterm;
    private String alttermh;
}