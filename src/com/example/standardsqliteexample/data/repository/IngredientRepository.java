package com.example.standardsqliteexample.data.repository;

import java.util.List;

import android.content.Context;

import com.example.standardsqliteexample.entity.Ingredient;
	

public class IngredientRepository extends RepositoryBase<Ingredient> implements IIngredientRepository {

	public IngredientRepository(Context context, Class<Ingredient> clazz) {
		super(context, clazz);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Ingredient entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Ingredient entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Ingredient getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ingredient> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
