package com.vavilon.demo.bo.announcment;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AnnouncementOverviewItem.class)
public abstract class AnnouncementOverviewItem_ {

	public static volatile SingularAttribute<AnnouncementOverviewItem, String> image;
	public static volatile SingularAttribute<AnnouncementOverviewItem, String> moderationStatusName;
	public static volatile SingularAttribute<AnnouncementOverviewItem, String> currencySign;
	public static volatile SingularAttribute<AnnouncementOverviewItem, Double> rating;
	public static volatile SingularAttribute<AnnouncementOverviewItem, Long> moderationStatusId;
	public static volatile SingularAttribute<AnnouncementOverviewItem, String> userName;
	public static volatile SingularAttribute<AnnouncementOverviewItem, Long> userId;
	public static volatile SingularAttribute<AnnouncementOverviewItem, String> productName;
	public static volatile SingularAttribute<AnnouncementOverviewItem, String> measure;
	public static volatile SingularAttribute<AnnouncementOverviewItem, String> measureCode;
	public static volatile SingularAttribute<AnnouncementOverviewItem, Double> price;
	public static volatile SingularAttribute<AnnouncementOverviewItem, Long> announcementId;
	public static volatile SingularAttribute<AnnouncementOverviewItem, String> text;

	public static final String IMAGE = "image";
	public static final String MODERATION_STATUS_NAME = "moderationStatusName";
	public static final String CURRENCY_SIGN = "currencySign";
	public static final String RATING = "rating";
	public static final String MODERATION_STATUS_ID = "moderationStatusId";
	public static final String USER_NAME = "userName";
	public static final String USER_ID = "userId";
	public static final String PRODUCT_NAME = "productName";
	public static final String MEASURE = "measure";
	public static final String MEASURE_CODE = "measureCode";
	public static final String PRICE = "price";
	public static final String ANNOUNCEMENT_ID = "announcementId";
	public static final String TEXT = "text";

}

