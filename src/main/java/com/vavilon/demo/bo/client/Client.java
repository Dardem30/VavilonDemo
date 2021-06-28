package com.vavilon.demo.bo.client;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "client")
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clientid")
    private Long clientId;
    @Column(name = "name")
    private String name;
    @Column(name = "inn")
    private String inn;
    @Column(name = "address")
    private String address;
    @Column(name = "clientdata")
    private String clientData;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "clientstatusid")
    private ClientStatus clientStatus;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "clienttypeid")
    private ClientType clientType;

}
