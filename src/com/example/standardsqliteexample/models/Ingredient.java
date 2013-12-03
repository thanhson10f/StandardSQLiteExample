package com.example.standardsqliteexample.models;

public class Ingredient {
	//private variables
	private String ingrId;
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
	
	
	
}
