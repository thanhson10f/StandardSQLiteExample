package com.example.standardsqliteexample;

import java.nio.ReadOnlyBufferException;
import java.util.List;

import com.example.standardsqliteexample.data.CookingContract.IngredientRecipeAscTb;
import com.example.standardsqliteexample.data.CookingContract.IngredientTb;
import com.example.standardsqliteexample.data.CookingContract.RecipeTb;
import com.example.standardsqliteexample.data.repository.*;
import com.example.standardsqliteexample.models.Ingredient;
import com.example.standardsqliteexample.models.IngredientRecipeAsc;
import com.example.standardsqliteexample.models.Recipe;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	IIngredientRepository rep;
	IRecipeRepository rep2;
	IIngredientRecipeAscRepository rep3;
	final String selectQuery = "SELECT "+"tb1."+IngredientTb.COLUMN_NAME_NAME+", "+"tb3."+RecipeTb.COLUMN_NAME_NAME+", "+
										"tb2."+IngredientRecipeAscTb.COLUMN_NAME_QUANTITY+" "+
								"FROM "+IngredientTb.TABLE_NAME+" tb1 "+" JOIN "+IngredientRecipeAscTb.TABLE_NAME+" tb2" + 
										" ON "+"tb1."+IngredientTb.COLUMN_NAME_INGR_ID + " = "+"tb2."+IngredientRecipeAscTb.COLUMN_NAME_INGR_ID+
										" JOIN "+RecipeTb.TABLE_NAME+" tb3"+
										" ON "+"tb2."+IngredientRecipeAscTb.COLUMN_NAME_RECIPE_ID + " = "+"tb3."+RecipeTb.COLUMN_NAME_RECIPE_ID;
										
								
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		rep = new IngredientRepository(this,Ingredient.class);
		rep2 = new RecipeRepository(this,Recipe.class);
		rep3 = new IngredientRecipeAscRepository(this, IngredientRecipeAsc.class);
		
		long rowId = rep.add(new Ingredient("1", "sonnt"));
		rep2.add(new Recipe("1","Ga hap gung"));
		rep3.add(new IngredientRecipeAsc("1","1",20));
		/*
		String[] projection = { IngredientTb._ID, IngredientTb.COLUMN_NAME_INGR_ID,
				IngredientTb.COLUMN_NAME_NAME };
		String selection = "LOWER("+IngredientTb.COLUMN_NAME_NAME + ")" + " like LOWER(?)";
		String[] selectionArgs = { "sonnt" };
		List<Ingredient> list = rep.getBySelections(projection, selection,
				selectionArgs, null, null, null, null);
		
		long id = 0;
		if (list!=null && !list.isEmpty()) {
			Log.d("main", String.valueOf(list.size()));
			Ingredient obj = list.get(0);
			TextView tv = (TextView) findViewById(R.id.tvReturn);
			id= obj.get_id();
			tv.setText(obj.get_id()+"----"+obj.getIngrId() + "---" + obj.getName() + "---"+obj.getTotal());
		}
		
		Ingredient instance = rep.getById(id);
		Log.d("instance", instance.get_id()+ "-" +instance.getName() + "-");
		*/
		String[] selection = {};
		Cursor c = rep3.rawQuery(selectQuery,selection);
		
		if(c.moveToFirst()){
			Log.d("result", "has "+c.getFloat(2) +" "+c.getString(0)+" in "+c.getString(1));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
