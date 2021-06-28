package com.vavilon.demo.bo.product;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductOverviewItem.class)
public abstract class ProductOverviewItem_ {

	public static volatile SingularAttribute<ProductOverviewItem, Long> productId;
	public static volatile SingularAttribute<ProductOverviewItem, String> name;
	public static volatile SingularAttribute<ProductOverviewItem, String> description;
	public static volatile SingularAttribute<ProductOverviewItem, String> category;
	public static volatile SingularAttribute<ProductOverviewItem, Long> categoryId;

	public static final String PRODUCT_ID = "productId";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String CATEGORY = "category";
	public static final String CATEGORY_ID = "categoryId";

}

