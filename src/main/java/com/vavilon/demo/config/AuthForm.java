package com.vavilon.demo.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AuthForm {
    private String login;
    private String password;
}
