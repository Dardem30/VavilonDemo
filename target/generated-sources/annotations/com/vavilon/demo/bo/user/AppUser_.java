package com.vavilon.demo.bo.user;

import com.vavilon.demo.bo.enums.Role;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AppUser.class)
public abstract class AppUser_ {

	public static volatile SingularAttribute<AppUser, String> firstName;
	public static volatile SingularAttribute<AppUser, String> lastName;
	public static volatile SingularAttribute<AppUser, String> password;
	public static volatile SingularAttribute<AppUser, Role> role;
	public static volatile SingularAttribute<AppUser, Boolean> active;
	public static volatile SingularAttribute<AppUser, Date> activationCodeDateEnd;
	public static volatile SingularAttribute<AppUser, String> activationCode;
	public static volatile SingularAttribute<AppUser, String> login;
	public static volatile SingularAttribute<AppUser, Long> userId;
	public static volatile SingularAttribute<AppUser, String> email;

	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String PASSWORD = "password";
	public static final String ROLE = "role";
	public static final String ACTIVE = "active";
	public static final String ACTIVATION_CODE_DATE_END = "activationCodeDateEnd";
	public static final String ACTIVATION_CODE = "activationCode";
	public static final String LOGIN = "login";
	public static final String USER_ID = "userId";
	public static final String EMAIL = "email";

}

