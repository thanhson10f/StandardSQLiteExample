package com.example.standardsqliteexample.data;

public interface IRepository<T>{
	
	//Add an entity
	public long add(T entity);
	
	//Update an entity
	public void update(T entity);
	
	//Delete an entity
	public void delete(T entiry);
	
	//Get an entity by Id
	public T getById(String id);
	
	//Get an entity by Id
	public T getById(long id);
	
	//Get All entities
	public Iterable<T> getAll();
}
