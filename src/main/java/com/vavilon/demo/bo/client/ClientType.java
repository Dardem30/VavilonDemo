package com.vavilon.demo.bo.client;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "clienttype")
@Data
public class ClientType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clienttypeid")
    private Long clientTypeId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
}
