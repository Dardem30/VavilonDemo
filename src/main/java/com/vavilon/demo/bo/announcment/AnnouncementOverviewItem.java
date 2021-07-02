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
    @Column(name = "image")
    private String image;
    @Column(name = "productname")
    private String productName;
    @Column(name = "text")
    private String text;
    @Column(name = "moderationstatusid")
    private Long moderationStatusId;
}
