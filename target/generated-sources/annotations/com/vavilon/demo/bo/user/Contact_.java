package com.vavilon.demo.bo.user;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Contact.class)
public abstract class Contact_ {

	public static volatile SingularAttribute<Contact, Long> contactId;
	public static volatile SingularAttribute<Contact, String> name;
	public static volatile SingularAttribute<Contact, ContactType> contactType;
	public static volatile SingularAttribute<Contact, Long> userId;
	public static volatile SingularAttribute<Contact, String> value;

	public static final String CONTACT_ID = "contactId";
	public static final String NAME = "name";
	public static final String CONTACT_TYPE = "contactType";
	public static final String USER_ID = "userId";
	public static final String VALUE = "value";

}

