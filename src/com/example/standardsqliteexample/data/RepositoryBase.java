package com.example.standardsqliteexample.data;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.example.standardsqliteexample.core.Utilities;

import android.app.DownloadManager.Query;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RepositoryBase<T> {
	private CookingDbHelper dbHelper;
	private final Class<T> clazz;
	private final String TABLE_NAME;

	protected RepositoryBase(Context context, Class<T> clazz) {
		dbHelper = CookingDbHelper.getInstance(context);
		this.clazz = clazz;
		this.TABLE_NAME = clazz.getName();
	}

	protected long Add(T entity) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.putAll(getContentValues(entity));

		long newRowId = db.insert(this.TABLE_NAME, null, values);
		return newRowId;
		
	}
	
	protected List<T> getBySelections(String[] projection,String selection,String[] selectionArgs,String groupBy,String having, String orderBy,String limit){
		
		List<T> list = new ArrayList<T>();
		
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		
		Cursor c = db.query(this.TABLE_NAME, projection, selection, selectionArgs, groupBy, having, orderBy, limit);
		
		list = getListFromCursor(c);
		
		return list;
	}
	
	private ContentValues getContentValues(T clazz) {
		ContentValues values = new ContentValues();
		
		for (Field field : clazz.getClass().getDeclaredFields()) {
			String fieldName = field.getName();
			Class<?> fieldType = field.getType();

			Method getMethod = null;
			
			try {
				getMethod = clazz.getClass().getMethod("get"
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
	
	private List<T> getListFromCursor(Cursor c) {
		List<T> list = new ArrayList<T>();
		String[] projection = c.getColumnNames();
		T instance = null;
		
		if(c.moveToFirst()){
			do{
				try {
					instance = clazz.newInstance();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				for(String col : projection){
					
					
					Field field = null;
					try {
						field = instance.getClass().getDeclaredField(col);
					} catch (NoSuchFieldException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					Class<?> fieldType = field.getType();
					
					String fieldTypeName = fieldType.getName().toLowerCase(
							Locale.getDefault());
					
					Method setMethod = null;
					try {
						setMethod = instance.getClass().getMethod("set"
								+ Utilities.capitalizeFirstLetter(col));
					} catch (NoSuchMethodException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					Object cursorReturn = null;
					Method cursorMethod = null;
					try {
						cursorMethod = c.getClass().getMethod("get"+Utilities.capitalizeFirstLetter(fieldTypeName));
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try {
						setMethod.invoke(null,cursorMethod.invoke(cursorReturn, c.getColumnIndex(col)));
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
					
				}
				list.add(instance);
			}while(c.moveToNext());
		}
		
		return list;
	}
}
