package com.vavilon.demo.bo.types;

import java.sql.Types;

public class PostgreSQL94Dialect extends org.hibernate.dialect.PostgreSQL94Dialect {

	public PostgreSQL94Dialect() {
		this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
	}
}