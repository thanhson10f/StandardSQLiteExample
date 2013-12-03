package com.example.standardsqliteexample;

import java.util.List;

import com.example.standardsqliteexample.data.CookingContract.IngredientTb;
import com.example.standardsqliteexample.data.IIngredientRepository;
import com.example.standardsqliteexample.data.IngredientRepository;
import com.example.standardsqliteexample.models.Ingredient;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        IIngredientRepository rep = new IngredientRepository(this,Ingredient.class);
        long rowId = rep.add(new Ingredient("id1","Phap"));
        
        String[] projection = {IngredientTb.COLUMN_NAME_INGR_ID,IngredientTb.COLUMN_NAME_NAME};
        String selection = IngredientTb.COLUMN_NAME_INGR_ID + "like ?";
        String[] selectionArgs = {"Phap"};
        List<Ingredient> list = rep.getBySelections(projection, selection, selectionArgs, null, null, null, null);
        
        Ingredient obj = list.get(0);
        TextView tv = (TextView) findViewById(R.id.tvReturn);
        
        tv.setText(String.valueOf(rowId)+ "---" + obj.getName());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
