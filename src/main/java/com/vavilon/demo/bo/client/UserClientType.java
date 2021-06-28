package com.vavilon.demo.bo.client;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "userclienttype")
@Data
public class UserClientType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userclienttypeid")
    private Long userClientTypeId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
}
