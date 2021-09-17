package com.vavilon.demo.bo.product;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ {

	public static volatile SingularAttribute<Product, Long> productId;
	public static volatile SingularAttribute<Product, String> name;
	public static volatile SingularAttribute<Product, String> description;
	public static volatile SingularAttribute<Product, Long> userId;
	public static volatile SingularAttribute<Product, ProductCategory> productCategory;

	public static final String PRODUCT_ID = "productId";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String USER_ID = "userId";
	public static final String PRODUCT_CATEGORY = "productCategory";

}

