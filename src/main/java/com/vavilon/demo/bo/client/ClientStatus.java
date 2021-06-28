package com.vavilon.demo.bo.client;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "clientstatus")
@Data
public class ClientStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clientstatusid")
    private Long clientStatusId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
}
