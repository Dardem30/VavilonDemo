package com.vavilon.demo.controller.util;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestBodyWithConfidentialInfo<T> {
    private List<Long> userIds;
    private Long requestOwnerId;
    private T body;
}
