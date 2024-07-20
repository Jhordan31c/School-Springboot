package com.schoolbackend.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimpleGrantedAuthorityJasonCreator {
    @JsonCreator
    public SimpleGrantedAuthorityJasonCreator(@JsonProperty("authority") String role){}  
}
