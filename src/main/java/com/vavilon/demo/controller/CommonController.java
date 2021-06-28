package com.vavilon.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vavilon.demo.bo.bean.ResponseForm;
import com.vavilon.demo.service.util.UtilityService;
import com.vavilon.demo.util.IConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class CommonController {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UtilityService utilityService;
    protected final Logger logger = LoggerFactory.getLogger(CommonController.class);

    protected <T> void writeResponseAsJSON(final ResponseForm<T> form, final HttpServletResponse response, final BiConsumer<Map<String, Object>, T> fillContentFunc) {

        response.setCharacterEncoding("UTF-8");
        response.setContentType(IConstants.APPLICATION_JSON_CHARSET_UTF_8);
        response.setStatus(HttpServletResponse.SC_OK);

        try {
            final Map<String, Object> content = new HashMap<>();
            content.put("message", form.getMessage());
            content.put("success", form.isSuccess());
            if (fillContentFunc != null) {
                fillContentFunc.accept(content, form.getResult());
            }
            objectMapper.writeValue(response.getWriter(), content);
        } catch (IOException e) {
            logger.error(form.getMessage(), e);
        }
    }
    protected <T> List<T> readAll(final Class<T> clazz) {
        return utilityService.readAll(clazz);
    }
}
