package com.vavilon.demo.bo;

import com.vavilon.demo.bo.user.AppUser;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PasswordResetToken.class)
public abstract class PasswordResetToken_ {

	public static volatile SingularAttribute<PasswordResetToken, Date> expiryDate;
	public static volatile SingularAttribute<PasswordResetToken, Long> passwordResetTokenId;
	public static volatile SingularAttribute<PasswordResetToken, AppUser> user;
	public static volatile SingularAttribute<PasswordResetToken, String> url;
	public static volatile SingularAttribute<PasswordResetToken, String> token;

	public static final String EXPIRY_DATE = "expiryDate";
	public static final String PASSWORD_RESET_TOKEN_ID = "passwordResetTokenId";
	public static final String USER = "user";
	public static final String URL = "url";
	public static final String TOKEN = "token";

}

