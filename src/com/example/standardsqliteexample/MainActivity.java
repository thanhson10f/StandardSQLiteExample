package com.example.standardsqliteexample;

import java.util.List;

import com.example.standardsqliteexample.data.CookingContract.IngredientTb;
import com.example.standardsqliteexample.data.IIngredientRepository;
import com.example.standardsqliteexample.data.IngredientRepository;
import com.example.standardsqliteexample.models.Ingredient;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		IIngredientRepository rep = new IngredientRepository(this,
				Ingredient.class);
		//long rowId = rep.add(new Ingredient("id1", "Phap"));

		String[] projection = { IngredientTb._ID, IngredientTb.COLUMN_NAME_INGR_ID,
				IngredientTb.COLUMN_NAME_NAME };
		String selection = "LOWER("+IngredientTb.COLUMN_NAME_NAME + ")" + " like LOWER(?)";
		String[] selectionArgs = { "pHap" };
		List<Ingredient> list = rep.getBySelections(projection, selection,
				selectionArgs, null, null, null, null);
		Log.d("main", String.valueOf(list.size()));
	
		if (list!=null && !list.isEmpty()) {
			Ingredient obj = list.get(0);
			TextView tv = (TextView) findViewById(R.id.tvReturn);

			tv.setText(obj.get_id()+"----"+obj.getIngrId() + "---" + obj.getName());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
