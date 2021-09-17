package com.vavilon.demo.bo.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "contact")
@Data
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contactid")
    private Long contactId;
    @Column(name = "userid")
    @JsonIgnore
    private Long userId;
    @Column(name = "name")
    private String name;
    @Column(name = "value")
    private String value;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "contacttypeid")
    private ContactType contactType;
}
