package com.vavilon.demo.bo.announcment;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "announcementratehistory")
@Getter
@Setter
public class AnnouncementRateHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcementratehistoryid")
    private Long announcementRateHistoryId;
    @Column(name = "userid")
    private Long userId;
    @Column(name = "announcementid")
    private Long announcementId;
    @Column(name = "rate")
    private Double rate;
}
