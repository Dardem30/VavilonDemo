package com.vavilon.demo.bo.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModerationForm {
    private Long announcementId;
    private Long moderationStatusId;
    private String text;
}
