package com.vavilon.demo.bo.client;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserClient.class)
public abstract class UserClient_ {

	public static volatile SingularAttribute<UserClient, Long> clientId;
	public static volatile SingularAttribute<UserClient, Long> userId;
	public static volatile SingularAttribute<UserClient, Long> userClientId;
	public static volatile SingularAttribute<UserClient, UserClientType> userClientType;

	public static final String CLIENT_ID = "clientId";
	public static final String USER_ID = "userId";
	public static final String USER_CLIENT_ID = "userClientId";
	public static final String USER_CLIENT_TYPE = "userClientType";

}

