package com.vavilon.demo.bo.bean;

import lombok.Data;

@Data
public class ResetPasswordForm {
    private Long userId;
    private String token;
    private String password;
}
