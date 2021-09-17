package com.vavilon.demo.controller.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    protected final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public @ResponseBody
    ErrorInfo handleConflict(HttpServletRequest req, Exception ex) {
        ErrorInfo errorInfo = null;
        try {
            Throwable rootCause = ex.getCause();
            if (rootCause != null)
                while (rootCause.getCause() != null && rootCause.getCause() != rootCause)
                    rootCause = rootCause.getCause();

            logger.error("The request " + req.getRequestURL() + " produced the Exception in Class: " + (rootCause != null ? rootCause.getStackTrace()[0].getClassName() : "") + ", Method: " + (rootCause != null ? rootCause.getStackTrace()[0].getMethodName() : "") + "", ex);
            errorInfo = new ErrorInfo(req.getRequestURL().toString(), ex);
        } catch (Exception e) {
            logger.error("Error during trying to handle exception of the request:" + req.getRequestURL(), e);
        }
        return errorInfo;
    }
}

class ErrorInfo {
    public String url;
    public String ex;

    public ErrorInfo() {

    }


    public ErrorInfo(String url, Exception ex) {
        this.url = url;
        this.ex = ex.getLocalizedMessage();
    }
}