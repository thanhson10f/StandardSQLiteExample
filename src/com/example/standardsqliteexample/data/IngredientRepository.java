package com.example.standardsqliteexample.data;

import java.util.List;

import android.content.Context;

import com.example.standardsqliteexample.models.Ingredient;
	

public class IngredientRepository extends RepositoryBase<Ingredient> implements IIngredientRepository {

	public IngredientRepository(Context context, Class<Ingredient> clazz) {
		super(context, clazz);
		// TODO Auto-generated constructor stub
	}

	@Override
	public long add(Ingredient entity) {
		// TODO Auto-generated method stub
		return super.Add(entity);
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
	public Ingredient getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ingredient> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ingredient> getBySelections(String[] projection,String selection,String[] selectionArgs,String groupBy,String having, String orderBy,String limit) {
		// TODO Auto-generated method stub
		return super.getBySelections(projection, selection, selectionArgs, groupBy, having, orderBy, limit);
	}

	@Override
	public void delete(String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		super.delete(selection, selectionArgs);
		
	}

}
