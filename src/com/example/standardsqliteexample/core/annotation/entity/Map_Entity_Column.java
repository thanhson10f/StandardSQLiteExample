package com.example.standardsqliteexample.core.annotation.entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Map_Entity_Column {
	String name();
	boolean allowNull() default false;
	boolean isPrimaryKey() default false;
	boolean isUnique() default false;
	int length() default -1;
	String type() default "";
	String FK() default "";
	boolean autoIncrement() default false;
}
