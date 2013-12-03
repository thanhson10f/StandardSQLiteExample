package com.example.standardsqliteexample.data;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

import com.example.standardsqliteexample.core.Utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class RepositoryBase<T> {
	private CookingDbHelper dbHelper;
	private final Class<T> clazz;

	protected RepositoryBase(Context context, Class<T> clazz) {
		dbHelper = CookingDbHelper.getInstance(context);
		this.clazz = clazz;
	}

	public long Add(T entity) {
		return 2;
		/*
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.putAll(getContentValues(clazz));

		long newRowId = db.insert(clazz.getName(), null, values);
		return newRowId;
		*/
	}

	private ContentValues getContentValues(Class<T> clazz) {
		ContentValues values = new ContentValues();
		
		for (Field field : clazz.getDeclaredFields()) {
			String fieldName = field.getName();
			Class<?> fieldType = field.getType();

			Method getMethod = null;
			
			try {
				getMethod = clazz.getMethod("get"
						+ Utilities.capitalizeFirstLetter(fieldName));
			} catch (NoSuchMethodException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Object methodReturn = null;
			try {
				getMethod.invoke(methodReturn);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String fieldTypeName = fieldType.getName().toLowerCase(
					Locale.getDefault());

			if (fieldTypeName.equalsIgnoreCase("boolean")) {
				values.put(fieldName, (Boolean) methodReturn);
			}

			if (fieldTypeName.equalsIgnoreCase("byte")) {
				values.put(fieldName, (Byte) methodReturn);
			}

			if (fieldTypeName.equalsIgnoreCase("integer")) {
				values.put(fieldName, (Integer) methodReturn);
			}

			if (fieldTypeName.equalsIgnoreCase("long")) {
				values.put(fieldName, (Long) methodReturn);
			}

			if (fieldTypeName.equalsIgnoreCase("short")) {
				values.put(fieldName, (Short) methodReturn);
			}

			if (fieldTypeName.equalsIgnoreCase("string")) {
				values.put(fieldName, (String) methodReturn);
			}

			if (fieldTypeName.equalsIgnoreCase("boolean")) {
				values.put(fieldName, (Boolean) methodReturn);
			}

			if (fieldTypeName.equalsIgnoreCase("double")) {
				values.put(fieldName, (Double) methodReturn);
			}

			if (fieldTypeName.equalsIgnoreCase("float")) {
				values.put(fieldName, (Float) methodReturn);
			}
		}
		return values;
	}
}
