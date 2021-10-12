package com.vavilon.demo.bo.announcment;

import com.vavilon.demo.bo.bean.Address;
import com.vavilon.demo.bo.bean.Params;
import com.vavilon.demo.bo.client.UserClient;
import com.vavilon.demo.bo.order.Order;
import com.vavilon.demo.bo.product.Product;
import com.vavilon.demo.bo.user.Contact;
import com.vavilon.demo.bo.user.UserLight;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Announcement.class)
public abstract class Announcement_ {

	public static volatile SingularAttribute<Announcement, Product> product;
	public static volatile SingularAttribute<Announcement, Address> address;
	public static volatile SingularAttribute<Announcement, String> currencySign;
	public static volatile SetAttribute<Announcement, Polygon> polygons;
	public static volatile SingularAttribute<Announcement, Date> announcementDate;
	public static volatile SingularAttribute<Announcement, Double> rating;
	public static volatile SingularAttribute<Announcement, ModerationStatus> moderationStatus;
	public static volatile SingularAttribute<Announcement, Params> params;
	public static volatile SingularAttribute<Announcement, Measure> measure;
	public static volatile SingularAttribute<Announcement, UserClient> userClient;
	public static volatile SingularAttribute<Announcement, BigDecimal> price;
	public static volatile SingularAttribute<Announcement, Long> announcementId;
	public static volatile SingularAttribute<Announcement, AnnouncementType> announcementType;
	public static volatile SingularAttribute<Announcement, String> text;
	public static volatile SingularAttribute<Announcement, UserLight> user;
	public static volatile SetAttribute<Announcement, Contact> contacts;
	public static volatile SingularAttribute<Announcement, Order> order;

	public static final String PRODUCT = "product";
	public static final String ADDRESS = "address";
	public static final String CURRENCY_SIGN = "currencySign";
	public static final String POLYGONS = "polygons";
	public static final String ANNOUNCEMENT_DATE = "announcementDate";
	public static final String RATING = "rating";
	public static final String MODERATION_STATUS = "moderationStatus";
	public static final String PARAMS = "params";
	public static final String MEASURE = "measure";
	public static final String USER_CLIENT = "userClient";
	public static final String PRICE = "price";
	public static final String ANNOUNCEMENT_ID = "announcementId";
	public static final String ANNOUNCEMENT_TYPE = "announcementType";
	public static final String TEXT = "text";
	public static final String USER = "user";
	public static final String CONTACTS = "contacts";
	public static final String ORDER = "order";

}

