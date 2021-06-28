package com.vavilon.demo.bo.user;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ContactType.class)
public abstract class ContactType_ {

	public static volatile SingularAttribute<ContactType, String> name;
	public static volatile SingularAttribute<ContactType, String> description;
	public static volatile SingularAttribute<ContactType, Long> contactTypeId;

	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String CONTACT_TYPE_ID = "contactTypeId";

}

