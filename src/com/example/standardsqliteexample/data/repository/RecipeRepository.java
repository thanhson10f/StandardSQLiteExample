package com.example.standardsqliteexample.data.repository;

import java.util.List;

import com.example.standardsqliteexample.models.Recipe;

import android.content.Context;

public class RecipeRepository extends RepositoryBase<Recipe> implements IRecipeRepository{

	public RecipeRepository(Context context, Class<Recipe> clazz) {
		super(context, clazz);
		// TODO Auto-generated constructor stub
	}

	@Override
	public long add(Recipe entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(Recipe entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Recipe entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Recipe getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Recipe> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	


}
