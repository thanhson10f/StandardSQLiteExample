package com.example.standardsqliteexample.models;

import com.example.standardsqliteexample.core.annotation.entity.Map_Entity_Column;

public class Entity implements IEntity<Long>{
	
	@Map_Entity_Column(name = "_id",isPrimaryKey=true)
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
