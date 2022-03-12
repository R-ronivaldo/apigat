package com.example.apiexample.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResquestModel {
    private String email;
    private String domain;
}
