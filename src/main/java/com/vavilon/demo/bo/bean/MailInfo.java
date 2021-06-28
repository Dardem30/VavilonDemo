package com.vavilon.demo.bo.bean;

import lombok.Data;

import java.util.List;

@Data
public class MailInfo {
    public static final String ACTIVATION_CODE_MAIL_TEMPLATE = "mail_templates/activation_code.vm";
    public static final String RESTORE_PASSWORD_MAIL_TEMPLATE = "mail_templates/restore_password.vm";
    private String template;
    private String nameFrom;
    private String addressFrom;
    private String subject;
    private List<Recipient> recipients;
}
