package com.example.apiexample.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AtivoModel {
    private String email;
    private String domain;
}
