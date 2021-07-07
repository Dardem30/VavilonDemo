package com.vavilon.demo.bo.product;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @Column(name = "productid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "userid")
    private Long userId;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "productcategoryid")
    private ProductCategory productCategory;
}
