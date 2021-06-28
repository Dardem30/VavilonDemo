package com.vavilon.demo.bo.product;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vw_product_overview")
@Data
public class ProductOverviewItem {
    @Id
    @Column(name = "productid")
    private Long productId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "category")
    private String category;
    @Column(name = "categoryid")
    private Long categoryId;
}
