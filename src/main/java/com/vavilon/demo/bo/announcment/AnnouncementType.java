package com.vavilon.demo.bo.announcment;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "announcementtype")
@Data
public class AnnouncementType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcementtypeid")
    private Long announcementTypeId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
}
