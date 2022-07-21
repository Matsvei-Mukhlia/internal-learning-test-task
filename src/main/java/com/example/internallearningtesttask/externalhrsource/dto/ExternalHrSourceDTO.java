package com.example.internallearningtesttask.externalhrsource.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExternalHrSourceDTO {
    @JsonIgnore
    private String id;

    private String email;

    @JsonProperty("first_name")
    private String firstname;

    @JsonProperty("last_name")
    private String lastname;

    private String avatar;
}
