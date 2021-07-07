package com.vavilon.demo.bo.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vavilon.demo.bo.bean.Recipient;
import com.vavilon.demo.bo.enums.Role;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "`user`")
@Data
public class AppUser {
    @Id
    @Column(name = "userid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "active")
    private boolean active;
    @Column(name = "activationcode")
    @JsonIgnore
    private String activationCode;
    @Column(name = "activationcodedateend")
    private Date activationCodeDateEnd;
    @Column(name = "email")
    private String email;
    @Column(name = "`role`")
    @Enumerated(EnumType.STRING)
    private Role role;

    public Recipient toRecipient() {
        final Recipient recipient = new Recipient();
        recipient.setName(firstName + " " + lastName);
        recipient.setEmail(email);
        return recipient;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
}
