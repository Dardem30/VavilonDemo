package com.vavilon.demo.bo.client;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserClientType.class)
public abstract class UserClientType_ {

	public static volatile SingularAttribute<UserClientType, String> name;
	public static volatile SingularAttribute<UserClientType, String> description;
	public static volatile SingularAttribute<UserClientType, Long> userClientTypeId;

	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String USER_CLIENT_TYPE_ID = "userClientTypeId";

}

