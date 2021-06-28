package com.vavilon.demo.bo.announcment;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AnnouncementOverviewItem.class)
public abstract class AnnouncementOverviewItem_ {

	public static volatile SingularAttribute<AnnouncementOverviewItem, String> image;
	public static volatile SingularAttribute<AnnouncementOverviewItem, Long> announcementId;
	public static volatile SingularAttribute<AnnouncementOverviewItem, String> text;
	public static volatile SingularAttribute<AnnouncementOverviewItem, String> productName;

	public static final String IMAGE = "image";
	public static final String ANNOUNCEMENT_ID = "announcementId";
	public static final String TEXT = "text";
	public static final String PRODUCT_NAME = "productName";

}

