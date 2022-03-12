package com.example.apiexample.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseExternalApiModel {
    private String id;
    private boolean softselectorwarning;
    private int status;
    private String altterm;
    private String alttermh;
}