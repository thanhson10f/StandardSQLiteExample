package com.example.standardsqliteexample.entity;

import com.example.standardsqliteexample.core.annotation.entity.*;
import com.example.standardsqliteexample.data.CookingContract.IngredientTb;

@Map_Entity(name = IngredientTb.TABLE_NAME,primaryKey=IngredientTb._ID)
public class Ingredient extends Entity {
	//private variables

	@Map_Ignore
	private int total;
	
	@Map_Entity_Column(name = IngredientTb.COLUMN_NAME_INGR_ID,isUnique=true)
	private String ingrId;
	
	@Map_Entity_Column(name = IngredientTb.COLUMN_NAME_NAME)
	private String name;
	
	
	public Ingredient(){}
	
	public Ingredient(String ingrId, String name) {
		this.ingrId = ingrId;
		this.name = name;
	}

	/**
	 * @return the ingrId
	 */
	public String getIngrId() {
		return ingrId;
	}

	/**
	 * @param ingrId the ingrId to set
	 */
	public void setIngrId(String ingrId) {
		this.ingrId = ingrId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public int getTotal() {
		return this.total+3*2-5+1;
	}
	
}
