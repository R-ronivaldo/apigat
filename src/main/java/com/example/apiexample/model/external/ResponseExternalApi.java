package com.example.apiexample.model.external;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseExternalApi {
    private String systemid;
    private String storageid;
    private String name;
    private String description;
}