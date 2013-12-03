package com.example.standardsqliteexample;

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
        TextView tv = (TextView) findViewById(R.id.tvReturn);
        tv.setText(String.valueOf(rep.add(new Ingredient())));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
