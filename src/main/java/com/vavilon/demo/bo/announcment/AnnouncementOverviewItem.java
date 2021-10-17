package com.vavilon.demo.bo.announcment;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vw_announcement_overview")
@Data
public class AnnouncementOverviewItem {
    @Id
    @Column(name = "announcementid")
    private Long announcementId;
    @Column(name = "userid")
    private Long userId;
    @Column(name = "image")
    private String image;
    @Column(name = "productname")
    private String productName;
    @Column(name = "text")
    private String text;
    @Column(name = "moderationstatusid")
    private Long moderationStatusId;
    @Column(name = "price")
    private Double price;
    @Column(name = "currencysign")
    private String currencySign;
    @Column(name = "measurecode")
    private String measureCode;
    @Column(name = "measure")
    private String measure;
    @Column(name = "moderationstatusname")
    private String moderationStatusName;
    @Column(name = "username")
    private String userName;
    @Column(name = "ownerphoto")
    private String ownerPhoto;
    @Column(name = "rating")
    private Double rating;
    @Column(name = "readyforreview")
    private boolean readyForReview;
}
