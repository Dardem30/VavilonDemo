package com.vavilon.demo.bo.client;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Client.class)
public abstract class Client_ {

	public static volatile SingularAttribute<Client, Long> clientId;
	public static volatile SingularAttribute<Client, String> address;
	public static volatile SingularAttribute<Client, ClientType> clientType;
	public static volatile SingularAttribute<Client, String> name;
	public static volatile SingularAttribute<Client, String> inn;
	public static volatile SingularAttribute<Client, ClientStatus> clientStatus;
	public static volatile SingularAttribute<Client, String> clientData;

	public static final String CLIENT_ID = "clientId";
	public static final String ADDRESS = "address";
	public static final String CLIENT_TYPE = "clientType";
	public static final String NAME = "name";
	public static final String INN = "inn";
	public static final String CLIENT_STATUS = "clientStatus";
	public static final String CLIENT_DATA = "clientData";

}

