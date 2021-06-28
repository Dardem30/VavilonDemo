package com.vavilon.demo.bo.announcment;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ModerationStatus.class)
public abstract class ModerationStatus_ {

	public static volatile SingularAttribute<ModerationStatus, String> name;
	public static volatile SingularAttribute<ModerationStatus, String> description;
	public static volatile SingularAttribute<ModerationStatus, Long> moderationStatusId;

	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String MODERATION_STATUS_ID = "moderationStatusId";

}

