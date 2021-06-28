package com.vavilon.demo.bo.announcment;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AnnouncementType.class)
public abstract class AnnouncementType_ {

	public static volatile SingularAttribute<AnnouncementType, Long> announcementTypeId;
	public static volatile SingularAttribute<AnnouncementType, String> name;
	public static volatile SingularAttribute<AnnouncementType, String> description;

	public static final String ANNOUNCEMENT_TYPE_ID = "announcementTypeId";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";

}

