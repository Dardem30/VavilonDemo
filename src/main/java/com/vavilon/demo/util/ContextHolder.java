package com.vavilon.demo.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:mail.properties")
})
@Getter
public class ContextHolder {
    @Value("${mail.username}")
    private String mailUsername;
    @Value("${mail.password}")
    private String mailPassword;
    @Value("${mail.from}")
    private String mailFrom;
    @Value("${activation_code_lifespan_in_hours}")
    private Integer activationCodeLifespanInHours;
    @Value("${reset_password_token_lifespan_in_hours}")
    private Integer resetPasswordTokenLifespanInHours;
    @Value("${reset_password_url}")
    private String resetPasswordUrl;
    @Value("${activate_user_url}")
    private String activateUserUrl;
    @Value("${folder.product_images}")
    private String folderProductImages;

}
