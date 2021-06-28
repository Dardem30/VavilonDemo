package com.vavilon.demo.bo;

import com.vavilon.demo.bo.user.AppUser;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "passwordresettoken")
@Data
public class PasswordResetToken {
    @Id
    @Column(name = "passwordresettokenid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passwordResetTokenId;
    @Column(name = "token")
    private String token;
    @Column(name = "expirydate")
    private Date expiryDate;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "userid")
    private AppUser user;
    @Column(name = "url")
    private String url;
}
