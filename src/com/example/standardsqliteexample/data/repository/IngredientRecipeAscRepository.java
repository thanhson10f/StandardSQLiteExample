package com.example.standardsqliteexample.data.repository;

import java.util.List;

import android.content.Context;

import com.example.standardsqliteexample.models.IngredientRecipeAsc;

public class IngredientRecipeAscRepository extends RepositoryBase<IngredientRecipeAsc> implements IIngredientRecipeAscRepository{

	public IngredientRecipeAscRepository(Context context,
			Class<IngredientRecipeAsc> clazz) {
		super(context, clazz);
		// TODO Auto-generated constructor stub
	}

	@Override
	public long add(IngredientRecipeAsc entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(IngredientRecipeAsc entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(IngredientRecipeAsc entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IngredientRecipeAsc getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IngredientRecipeAsc> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
