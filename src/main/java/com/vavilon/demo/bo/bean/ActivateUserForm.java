package com.vavilon.demo.bo.bean;

import lombok.Data;

@Data
public class ActivateUserForm {
    private Long userId;
    private String activationCode;
}
