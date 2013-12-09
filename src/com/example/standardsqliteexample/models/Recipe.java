package com.example.standardsqliteexample.models;

import com.example.standardsqliteexample.core.annotation.entity.Map_Entity;
import com.example.standardsqliteexample.core.annotation.entity.Map_Entity_Column;
import com.example.standardsqliteexample.data.CookingContract.IngredientTb;
import com.example.standardsqliteexample.data.CookingContract.RecipeTb;

@Map_Entity(name = RecipeTb.TABLE_NAME,primaryKey=RecipeTb._ID)
public class Recipe extends Entity{
	
	@Map_Entity_Column(name=RecipeTb.COLUMN_NAME_RECIPE_ID,isUnique=true)
	private String recipeID;
	
	@Map_Entity_Column(name=RecipeTb.COLUMN_NAME_NAME)
	private String name;

	public Recipe(){}
	
	public Recipe(String recipeID, String name) {
		super();
		this.recipeID = recipeID;
		this.name = name;
	}

	/**
	 * @return the recipeID
	 */
	public String getRecipeID() {
		return recipeID;
	}

	/**
	 * @param recipeID the recipeID to set
	 */
	public void setRecipeID(String recipeID) {
		this.recipeID = recipeID;
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
	
	
}
