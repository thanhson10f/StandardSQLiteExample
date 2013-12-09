package com.example.standardsqliteexample.core.annotation.entity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Map_Entity {
	String name();
	String[] primaryKey() default {};
}
