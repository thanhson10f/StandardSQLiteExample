package com.example.standardsqliteexample.models;

public class Entity implements IEntity<Long>{
	protected Long _id;
	
	@Override
	public void set_id(Long _id){
		this._id = _id;
	}

	@Override
	public Long get_id(){
		return this._id;
	}

}
