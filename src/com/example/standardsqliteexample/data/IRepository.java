package com.example.standardsqliteexample.data;

import java.util.List;

public interface IRepository<T>{
	
	//Add an entity
	public long add(T entity);
	
	//Update an entity
	public void update(T entity);
	
	//Delete an entity
	public void delete(T entity);
	
	public void delete(String selection, String[] selectionArgs);
	
	//Get an entity by Id
	public T getById(String id);
	
	//Get an entity by Id
	public T getById(long id);
	
	public List<T> getBySelections(String[] projection,String selection,String[] selectionArgs,String groupBy,String having, String orderBy,String limit);
	
	//Get All entities
	public List<T> getAll();
}
