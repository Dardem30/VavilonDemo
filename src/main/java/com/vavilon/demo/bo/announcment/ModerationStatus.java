package com.vavilon.demo.bo.announcment;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "moderationstatus")
@Data
public class ModerationStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "moderationstatusid")
    private Long moderationStatusId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
}
