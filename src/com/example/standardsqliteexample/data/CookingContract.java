package com.example.standardsqliteexample.data;

import android.provider.BaseColumns;

public class CookingContract {

	public CookingContract(){}
	
	/* Inner class that defines the table ingredient contents */
	public static abstract class IngredientTb implements BaseColumns {
		public static final String TABLE_NAME = "ingredient";
		public static final String COLUMN_NAME_INGR_ID = "ingrId";
		public static final String COLUMN_NAME_NAME = "name";
	}
}
