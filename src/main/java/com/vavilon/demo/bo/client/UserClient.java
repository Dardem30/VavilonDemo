package com.vavilon.demo.bo.client;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "userclient")
@Data
public class UserClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userclientid")
    private Long userClientId;
    @Column(name = "clientid")
    private Long clientId;
    @Column(name = "userid")
    private Long userId;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "userclienttypeid")
    private UserClientType userClientType;
}
