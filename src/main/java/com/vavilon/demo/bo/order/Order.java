package com.vavilon.demo.bo.order;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "`order`")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderid")
    private Long orderId;
    @Column(name = "orderdate")
    private Date orderDate;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "orderstatusid")
    private OrderStatus orderStatus;
}
