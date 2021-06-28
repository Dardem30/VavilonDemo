package com.vavilon.demo.bo.product;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "productcategory")
@Data
public class ProductCategory {
    @Id
    @Column(name = "productcategoryid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productCategoryId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
}
