package com.vavilon.demo.service;

import com.vavilon.demo.bo.PasswordResetToken;
import com.vavilon.demo.bo.bean.ActivateUserForm;
import com.vavilon.demo.bo.bean.ResetPasswordForm;
import com.vavilon.demo.bo.bean.ResponseForm;
import com.vavilon.demo.bo.enums.Role;
import com.vavilon.demo.bo.user.AppUser;
import com.vavilon.demo.da.AppUserRepository;
import com.vavilon.demo.da.PasswordResetTokenRepository;
import com.vavilon.demo.util.ContextHolder;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
public class UserService {
    protected final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final EmailService emailService;
    private final AppUserRepository userRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ContextHolder contextHolder;

    @Transactional(readOnly = true)
    public AppUser findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Transactional
    public ResponseForm<AppUser> registrateUser(final AppUser user) throws Exception {
        if (userRepository.existsByLogin(user.getLogin())) {
            return new ResponseForm<>("User with login[" + user.getLogin() + "] already exists", false);
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            return new ResponseForm<>("User with email[" + user.getEmail() + "] already exists", false);
        }
        final Calendar activationCodeDateEnd = Calendar.getInstance();
        activationCodeDateEnd.add(Calendar.HOUR, contextHolder.getActivationCodeLifespanInHours());
        user.setActive(false);
        user.setRole(Role.USER);
        user.setActivationCode(UUID.randomUUID().toString());
        user.setActivationCodeDateEnd(activationCodeDateEnd.getTime());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        try {
            emailService.sendActivationCode(user, new Locale("en"));//@ToDO correctly determine locale
        } catch (final Exception e) {
            logger.error("Failed to send activation code to user [login: " + user.getLogin() + ", email: " + user.getEmail() + "]", e);
            throw e;
        }
        return new ResponseForm<>("User is successfully saved", true, user);
    }

    @Transactional
    public ResponseForm<AppUser> activateUser(final ActivateUserForm activateUserForm) {
        final Optional<AppUser> user = userRepository.findById(activateUserForm.getUserId());
        if (user.isPresent()) {
            final AppUser appUser = user.get();
            if (activateUserForm.getActivationCode().equals(appUser.getActivationCode())) {
                if (appUser.getActivationCodeDateEnd().getTime() < new Date().getTime()) {
                    return new ResponseForm<>("Failed to activate user: activation code is expired", false, appUser);
                }
                appUser.setActive(true);
                userRepository.save(appUser);
                return new ResponseForm<>("User is successfully activated", true, appUser);
            }
            return new ResponseForm<>("Failed to activate user: wrong activation code", false, appUser);
        }
        return new ResponseForm<>("Failed to activate user: user with provided id doesn't exists", false);
    }

    @Transactional(readOnly = true)
    public AppUser getUser(final Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Transactional
    public ResponseForm<PasswordResetToken> sendResetPasswordEmail(final String email) throws Exception {
        final AppUser user = userRepository.findByEmail(email);
        if (user == null) {
            return new ResponseForm<>("User with email[" + email + "] doesn't exist", false);
        }
        final PasswordResetToken passwordResetTokenEntry = new PasswordResetToken();
        final Calendar tokenDateExpiry = Calendar.getInstance();
        tokenDateExpiry.add(Calendar.HOUR, contextHolder.getResetPasswordTokenLifespanInHours());
        passwordResetTokenEntry.setExpiryDate(tokenDateExpiry.getTime());
        passwordResetTokenEntry.setUser(user);
        passwordResetTokenEntry.setToken(UUID.randomUUID().toString());
        passwordResetTokenEntry.setUrl(contextHolder.getResetPasswordUrl());
        passwordResetTokenRepository.save(passwordResetTokenEntry);
        emailService.sendResetPasswordMail(passwordResetTokenEntry, new Locale("en"));
        return new ResponseForm<>("Reset password mail is sent", true, passwordResetTokenEntry);
    }

    @Transactional
    public ResponseForm<PasswordResetToken> resetPassword(final ResetPasswordForm resetPasswordForm) {
        final PasswordResetToken passwordResetTokenEntry = passwordResetTokenRepository.findByUser_UserIdAndAndToken(resetPasswordForm.getUserId(), resetPasswordForm.getToken());
        if (passwordResetTokenEntry != null) {
            if (passwordResetTokenEntry.getExpiryDate().getTime() > new Date().getTime()) {
                final AppUser user = passwordResetTokenEntry.getUser();
                user.setPassword(bCryptPasswordEncoder.encode(resetPasswordForm.getPassword()));
                userRepository.save(user);
                passwordResetTokenRepository.delete(passwordResetTokenEntry);
                return new ResponseForm<>("Password is changed successfully", true, passwordResetTokenEntry);
            }
            return new ResponseForm<>("The token is expired", false, passwordResetTokenEntry);
        }
        return new ResponseForm<>("Couldn't find the token with userId[" + resetPasswordForm.getUserId() + "]", false);
    }
}