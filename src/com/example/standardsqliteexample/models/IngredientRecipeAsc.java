package com.example.standardsqliteexample.models;

import com.example.standardsqliteexample.core.annotation.entity.Map_Entity;
import com.example.standardsqliteexample.core.annotation.entity.Map_Entity_Column;
import com.example.standardsqliteexample.data.CookingContract.IngredientRecipeAscTb;
import com.example.standardsqliteexample.data.CookingContract.RecipeTb;

@Map_Entity(name = IngredientRecipeAscTb.TABLE_NAME,primaryKey=IngredientRecipeAscTb._ID)
public class IngredientRecipeAsc extends Entity {

	
	
	@Map_Entity_Column(
			name = IngredientRecipeAscTb.COLUMN_NAME_RECIPE_ID,
			FK=RecipeTb.TABLE_NAME+"(" +RecipeTb.COLUMN_NAME_RECIPE_ID+")")
	private String recipeId;
	
	@Map_Entity_Column(name = IngredientRecipeAscTb.COLUMN_NAME_INGR_ID)
	private String ingrId;
	
	@Map_Entity_Column(name = IngredientRecipeAscTb.COLUMN_NAME_QUANTITY)
	private float quantity;

	public IngredientRecipeAsc(String recipeId, String ingrId, float quantity) {
		super();
		this.recipeId = recipeId;
		this.ingrId = ingrId;
		this.quantity = quantity;
	}
	
	public IngredientRecipeAsc(){}

	/**
	 * @return the recipeId
	 */
	public String getRecipeId() {
		return recipeId;
	}

	/**
	 * @param recipeId the recipeId to set
	 */
	public void setRecipeId(String recipeId) {
		this.recipeId = recipeId;
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
	 * @return the quantity
	 */
	public float getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	};
	
	
}
