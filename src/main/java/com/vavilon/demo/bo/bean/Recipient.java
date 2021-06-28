package com.vavilon.demo.bo.bean;

import com.vavilon.demo.bo.enums.RecipientType;
import lombok.Data;

@Data
public class Recipient {
    private String name;
    private String email;
    private RecipientType type;
}
