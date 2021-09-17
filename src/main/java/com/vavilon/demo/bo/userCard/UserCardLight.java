package com.vavilon.demo.bo.userCard;

import com.vavilon.demo.bo.enums.ActionType;
import com.vavilon.demo.bo.enums.UserType;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "usercard")
@Data
public class UserCardLight {
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
    @Column(name = "userid")
    private Long userId;
}
