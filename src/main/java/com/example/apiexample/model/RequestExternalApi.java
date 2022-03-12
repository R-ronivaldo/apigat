package com.example.apiexample.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RequestApi {
    private String term;
    private int lookuplevel;
    private int maxresults;
    private int timeout;
    private String datefrom;
    private String dateto;
    private int sort;
    private int media;
    private List<String> terminate;
}