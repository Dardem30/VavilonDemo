package com.vavilon.demo.bo.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class Address implements Serializable {
    private String city;
    private String country;
    private String street;
}
