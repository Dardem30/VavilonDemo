package com.vavilon.demo.bo.bean;

import lombok.Data;

@Data
public class ResponseForm<T> {
    private String message;
    private boolean success;
    private T result;

    public ResponseForm(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public ResponseForm(String message, boolean success, T result) {
        this.message = message;
        this.success = success;
        this.result = result;
    }
}
