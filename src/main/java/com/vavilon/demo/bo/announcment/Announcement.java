package com.vavilon.demo.bo.announcment;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vavilon.demo.bo.bean.Address;
import com.vavilon.demo.bo.bean.Params;
import com.vavilon.demo.bo.client.UserClient;
import com.vavilon.demo.bo.jackson.JsonDateDeserializer;
import com.vavilon.demo.bo.jackson.JsonDateSerializer;
import com.vavilon.demo.bo.order.Order;
import com.vavilon.demo.bo.product.Product;
import com.vavilon.demo.bo.user.Contact;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

@Entity
@Table(name = "announcement")
@Data
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcementid")
    private Long announcementId;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "userclientid")
    private UserClient userClient;
    @Column(name = "address")
    @Type(type = "JsonBAddressType")
    private Address address;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "contactid")
    private Contact contact;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "announcementtypeid")
    private AnnouncementType announcementType;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "productid")
    private Product product;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "measurecode")
    private Measure measure;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "currencysign")
    private String currencySign;
    @Column(name = "text")
    private String text;
    @Column(name = "params")
    @Type(type = "JsonBParamsType")
    private Params params;
    @Column(name = "announcementdate")
    @JsonSerialize(using= JsonDateSerializer.class)
    @JsonDeserialize(using= JsonDateDeserializer.class)
    private Date announcementDate;
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "orderid")
    private Order order;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "moderationstatusid")
    private ModerationStatus moderationStatus;

}
