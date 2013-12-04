package com.example.standardsqliteexample.models;

public interface IEntity<TPrimaryKey>{  
	
     public void set_id(TPrimaryKey _id);
     public TPrimaryKey get_id();
}  
