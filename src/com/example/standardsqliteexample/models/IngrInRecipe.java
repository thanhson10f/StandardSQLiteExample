package com.example.standardsqliteexample.models;

public class IngrInRecipe {
	private String ingrName;
	private String recipeName;
	private float quantity;
	
	public IngrInRecipe(){}
	
	public IngrInRecipe(String ingrName, String recipeName, float quantity) {
		super();
		this.ingrName = ingrName;
		this.recipeName = recipeName;
		this.quantity = quantity;
	}
	/**
	 * @return the ingrName
	 */
	public String getIngrName() {
		return ingrName;
	}
	/**
	 * @param ingrName the ingrName to set
	 */
	public void setIngrName(String ingrName) {
		this.ingrName = ingrName;
	}
	/**
	 * @return the recipeName
	 */
	public String getRecipeName() {
		return recipeName;
	}
	/**
	 * @param recipeName the recipeName to set
	 */
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
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
	}
	
	
}
