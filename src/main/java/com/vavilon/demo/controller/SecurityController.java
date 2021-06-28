package com.vavilon.demo.controller;

import com.vavilon.demo.bo.user.AppUser;
import com.vavilon.demo.bo.PasswordResetToken;
import com.vavilon.demo.bo.bean.ActivateUserForm;
import com.vavilon.demo.bo.bean.ResetPasswordForm;
import com.vavilon.demo.bo.bean.ResponseForm;
import com.vavilon.demo.service.UserService;
import com.vavilon.demo.service.security.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@AllArgsConstructor
public class SecurityController extends CommonController {
    private final UserService userService;

    @GetMapping(path = "/home")
    public ResponseEntity<AppUser> home() {
        try {
            return ResponseEntity.ok(userService.getUser(User.get().getAppUser().getUserId()));
        } catch (final Exception e) {
            return new ResponseEntity("Username and/or password is incorrect", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(path = "/registration")
    public void registration(@RequestBody final AppUser appUser, final HttpServletResponse response) {
        try {
            final ResponseForm<AppUser> form = userService.registrateUser(appUser);
            writeResponseAsJSON(form, response, (content, user) -> {
                if (user != null) {
                    content.put("userId", user.getUserId());
                }
            });
        } catch (final Exception e) {
            logger.error("Failed to save user", e);
            writeResponseAsJSON(new ResponseForm<>("Failed to save user", false), response, null);
        }
    }
    @PostMapping(path = "/activateUser")
    public void activateUser(@RequestBody final ActivateUserForm activateUserForm, final HttpServletResponse response) {
        try {
            final ResponseForm<AppUser> form = userService.activateUser(activateUserForm);
            writeResponseAsJSON(form, response, null);
        } catch (final Exception e) {
            logger.error("Failed to activate user", e);
            writeResponseAsJSON(new ResponseForm<>("Failed to activate user", false), response, null);
        }
    }
    @PostMapping(path = "/sendResetPasswordEmail")
    public void sendResetPasswordEmail(@RequestBody String email, final HttpServletResponse response) {
        try {
            final ResponseForm<PasswordResetToken> form = userService.sendResetPasswordEmail(email);
            writeResponseAsJSON(form, response, (content, passwordResetToken) -> {
                if (passwordResetToken != null) {
                    content.put("expiryDate", passwordResetToken.getExpiryDate());
                }
            });
        } catch (final Exception e) {
            logger.error("Failed to send reset password email to a user with email [" + email + "]", e);
            writeResponseAsJSON(new ResponseForm<>("Failed to send reset password email to a user", false), response, (content, nullObj) -> {
                content.put("email", email);
            });
        }
    }
    @PostMapping(path = "/resetPassword")
    public void resetPassword(@RequestBody ResetPasswordForm resetPasswordForm, final HttpServletResponse response) {
        try {
            final ResponseForm<PasswordResetToken> form = userService.resetPassword(resetPasswordForm);
            writeResponseAsJSON(form, response, null);
        } catch (final Exception e) {
            logger.error("Failed to reset password for user with id [" + resetPasswordForm.getUserId() + "]", e);
            writeResponseAsJSON(new ResponseForm<>("Failed to send reset password email to a user", false), response, (content, nullObj) -> {
                content.put("userId", resetPasswordForm.getUserId());
            });
        }
    }

}
