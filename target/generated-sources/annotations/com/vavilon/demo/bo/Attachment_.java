package com.vavilon.demo.bo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Attachment.class)
public abstract class Attachment_ {

	public static volatile SingularAttribute<Attachment, Long> productId;
	public static volatile SingularAttribute<Attachment, Boolean> main;
	public static volatile SingularAttribute<Attachment, Long> attachmentId;
	public static volatile SingularAttribute<Attachment, String> fileId;

	public static final String PRODUCT_ID = "productId";
	public static final String MAIN = "main";
	public static final String ATTACHMENT_ID = "attachmentId";
	public static final String FILE_ID = "fileId";

}

