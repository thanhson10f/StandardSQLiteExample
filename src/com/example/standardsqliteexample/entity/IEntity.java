package com.example.standardsqliteexample.entity;

public interface IEntity<TPrimaryKey>{  
	
     public void set_id(TPrimaryKey _id);
     public TPrimaryKey get_id();
}  
