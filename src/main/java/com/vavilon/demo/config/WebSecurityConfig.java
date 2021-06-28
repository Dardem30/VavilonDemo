package com.vavilon.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vavilon.demo.service.security.UserDetailsService;
import com.vavilon.demo.util.IConstants;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;
    @Bean
    public RequestBodyReaderAuthenticationFilter authenticationFilter() throws Exception {
        RequestBodyReaderAuthenticationFilter authenticationFilter
                = new RequestBodyReaderAuthenticationFilter();
        authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
        authenticationFilter.setAuthenticationManager(authenticationManagerBean());
        authenticationFilter.setAuthenticationSuccessHandler(this::loginSuccessHandler);
        authenticationFilter.setAuthenticationFailureHandler(this::loginFailureHandler);
        return authenticationFilter;
    }
    private void loginSuccessHandler(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException {

        final Map<String, Object> content = new HashMap<>(3);
        content.put("message", "Authentication is successful");
        content.put("code", HttpStatus.OK.value());
        content.put("success", true);
        response.setCharacterEncoding("UTF-8");
        response.setContentType(IConstants.APPLICATION_JSON_CHARSET_UTF_8);
        response.setStatus(HttpStatus.OK.value());
        objectMapper.writeValue(response.getWriter(), content);
    }

    private void loginFailureHandler(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException e) throws IOException {

        final Map<String, Object> content = new HashMap<>(3);
        content.put("message", "Authentication is failed");
        content.put("code", HttpStatus.UNAUTHORIZED.value());
        content.put("success", false);
        response.setCharacterEncoding("UTF-8");
        response.setContentType(IConstants.APPLICATION_JSON_CHARSET_UTF_8);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        objectMapper.writeValue(response.getWriter(), content);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/registration").permitAll()
                .antMatchers("/sendResetPasswordEmail").permitAll()
                .antMatchers("/resetPassword").permitAll()
                .antMatchers("/activateUser").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(
                        authenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/logout") //the URL on which the clients should post if they want to logout
                .logoutSuccessHandler(this::logoutSuccessHandler)
                .invalidateHttpSession(true);

    }
    private void logoutSuccessHandler(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) {
        response.setStatus(HttpStatus.OK.value());
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return authProvider;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider());
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/login").allowedMethods("POST").allowedOrigins("http://localhost:63342");
            }
        };
    }
}