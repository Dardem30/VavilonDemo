package com.vavilon.demo.service.security;

import com.vavilon.demo.bo.user.AppUser;
import com.vavilon.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
        final AppUser user = userService.findByLoginOrEmail(login, login);
        if (user == null) {
           throw new BadCredentialsException("User with login [" + login + "] doesn't exist");
        }
        if (!user.isActive()) {
           throw new BadCredentialsException("User with login [" + login + "] is not active");
        }
        return new User(user);
    }
}
