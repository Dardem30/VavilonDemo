package com.vavilon.demo.bo.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vw_profile_info")
@Getter
@Setter
public class ProfileInfo {
    @Id
    @Column(name = "userid")
    private Long userId;
    @Column(name = "info")
    private String info;
    @Column(name = "username")
    private String username;
    @Column(name = "photo")
    private String photo;
    @Column(name = "countannouncements")
    private Integer countAnnouncements;
    @Column(name = "countcomments")
    private Integer countComments;
    @Column(name = "averagerate")
    private Double averageRate;
}
