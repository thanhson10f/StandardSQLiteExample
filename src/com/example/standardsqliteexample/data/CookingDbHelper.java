package com.example.standardsqliteexample.data;


import com.example.standardsqliteexample.core.Utilities;
import com.example.standardsqliteexample.data.CookingContract.IngredientTb;
import com.example.standardsqliteexample.entity.Ingredient;
import com.example.standardsqliteexample.entity.IngredientRecipeAsc;
import com.example.standardsqliteexample.entity.Recipe;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CookingDbHelper extends SQLiteOpenHelper {
	
	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	private static final String UNIQUE = " UNIQUE";
	
	/*private static final String SQL_CREATE_ENTRIES =
	    "CREATE TABLE " + IngredientTb.TABLE_NAME + " (" +
	    IngredientTb._ID + " INTEGER PRIMARY KEY," +
	    IngredientTb.COLUMN_NAME_INGR_ID + TEXT_TYPE + UNIQUE + COMMA_SEP +
	    IngredientTb.COLUMN_NAME_NAME + TEXT_TYPE+
	    // Any other options for the CREATE command
	    " )";*/
	private static final String SQL_CREATE_ENTRIES = Utilities.GenerateCREATESQLQuery(Ingredient.class);
	private static final String SQL_DELETE_ENTRIES =
	    "DROP TABLE IF EXISTS " + IngredientTb.TABLE_NAME;
	
	
	// If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Cooking.db";
    
    
    private Context mCxt;
    
    public static CookingDbHelper getInstance(Context context){
    	return LazyHolder.GetInstance(context);
    }
    
	private CookingDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
		this.mCxt = context;
	}
	
	private static class LazyHolder {
		private static CookingDbHelper mInstance = null;
		
		private LazyHolder(){}
		
		public static CookingDbHelper GetInstance(Context context){
			if(mInstance ==null){
	    		mInstance = new CookingDbHelper(context.getApplicationContext());
	    	}
			return mInstance;
		}
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(Utilities.GenerateCREATESQLQuery(Ingredient.class));
		db.execSQL(Utilities.GenerateCREATESQLQuery(Recipe.class));
		db.execSQL(Utilities.GenerateCREATESQLQuery(IngredientRecipeAsc.class));
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL(Utilities.GenerateDROPTABLESQL(IngredientRecipeAsc.class));
		db.execSQL(Utilities.GenerateDROPTABLESQL(Recipe.class));
		db.execSQL(Utilities.GenerateDROPTABLESQL(Ingredient.class));
		onCreate(db);		
	}

	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		onUpgrade(db, oldVersion, newVersion);
	}
	
	
	

}
