package com.vavilon.demo.bo.userCard;

import com.vavilon.demo.bo.enums.ActionType;
import com.vavilon.demo.bo.enums.Gender;
import com.vavilon.demo.bo.enums.UserType;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "usercard")
@Data
public class UserCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usercardid")
    private Long userCardId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "actiontype")
    @Enumerated(EnumType.STRING)
    private ActionType actionType;
    @Column(name = "usertype")
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @Column(name = "username")
    private String username;
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "birthdate")
    private Date birthdate;
    @Column(name = "inn")
    private String inn;
    @Column(name = "userid")
    private Long userId;
}
