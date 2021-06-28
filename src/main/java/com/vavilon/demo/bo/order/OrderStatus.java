package com.vavilon.demo.bo.order;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "orderstatus")
@Data
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderstatusid")
    private Long orderStatusId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
}
