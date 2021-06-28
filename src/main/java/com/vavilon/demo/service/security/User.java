package com.vavilon.demo.service.security;

import com.vavilon.demo.bo.user.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

public class User extends org.springframework.security.core.userdetails.User {
    private final AppUser appUser;

    private static final List<GrantedAuthority> predefinedAuthorities;
    static {
        // add USER_ROLE to all users as predefined
        predefinedAuthorities = new ArrayList<GrantedAuthority>();
        predefinedAuthorities.add( new SimpleGrantedAuthority("ROLE_USER"));
    }

    public User(AppUser appUser) {
        super(appUser.getLogin(), appUser.getPassword() , true, true, true, true, getUserAuthorities(appUser));
        this.appUser = appUser;
    }

    private static List<GrantedAuthority> getUserAuthorities(AppUser appUser) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.addAll(predefinedAuthorities);
        return authorities;
    }

    public AppUser getAppUser() {return appUser;}

    public static User get() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
