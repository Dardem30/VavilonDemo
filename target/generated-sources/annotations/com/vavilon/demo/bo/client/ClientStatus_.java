package com.vavilon.demo.bo.client;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ClientStatus.class)
public abstract class ClientStatus_ {

	public static volatile SingularAttribute<ClientStatus, Long> clientStatusId;
	public static volatile SingularAttribute<ClientStatus, String> name;
	public static volatile SingularAttribute<ClientStatus, String> description;

	public static final String CLIENT_STATUS_ID = "clientStatusId";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";

}

