package com.vavilon.demo.bo.order;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Order.class)
public abstract class Order_ {

	public static volatile SingularAttribute<Order, Long> orderId;
	public static volatile SingularAttribute<Order, OrderStatus> orderStatus;
	public static volatile SingularAttribute<Order, Date> orderDate;

	public static final String ORDER_ID = "orderId";
	public static final String ORDER_STATUS = "orderStatus";
	public static final String ORDER_DATE = "orderDate";

}

