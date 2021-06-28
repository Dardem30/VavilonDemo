package com.vavilon.demo.bo.product;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductCategory.class)
public abstract class ProductCategory_ {

	public static volatile SingularAttribute<ProductCategory, Long> productCategoryId;
	public static volatile SingularAttribute<ProductCategory, String> name;
	public static volatile SingularAttribute<ProductCategory, String> description;

	public static final String PRODUCT_CATEGORY_ID = "productCategoryId";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";

}

