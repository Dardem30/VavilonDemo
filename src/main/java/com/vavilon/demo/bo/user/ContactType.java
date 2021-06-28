package com.vavilon.demo.bo.user;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "contacttype")
@Data
public class ContactType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contacttypeid")
    private Long contactTypeId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
}
