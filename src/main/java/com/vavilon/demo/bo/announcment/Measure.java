package com.vavilon.demo.bo.announcment;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "measure")
@Data
public class Measure {
    @Id
    @Column(name = "measurecode")
    private String measureCode;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
}
