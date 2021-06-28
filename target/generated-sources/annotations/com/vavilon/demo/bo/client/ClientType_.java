package com.vavilon.demo.bo.client;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ClientType.class)
public abstract class ClientType_ {

	public static volatile SingularAttribute<ClientType, Long> clientTypeId;
	public static volatile SingularAttribute<ClientType, String> name;
	public static volatile SingularAttribute<ClientType, String> description;

	public static final String CLIENT_TYPE_ID = "clientTypeId";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";

}

