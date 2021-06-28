package com.vavilon.demo.bo.order;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderStatus.class)
public abstract class OrderStatus_ {

	public static volatile SingularAttribute<OrderStatus, Long> orderStatusId;
	public static volatile SingularAttribute<OrderStatus, String> name;
	public static volatile SingularAttribute<OrderStatus, String> description;

	public static final String ORDER_STATUS_ID = "orderStatusId";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";

}

