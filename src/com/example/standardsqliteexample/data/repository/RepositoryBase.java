package com.example.standardsqliteexample.data.repository;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.standardsqliteexample.core.Utilities;
import com.example.standardsqliteexample.core.annotation.entity.Map_Entity;
import com.example.standardsqliteexample.core.annotation.entity.Map_Entity_Column;
import com.example.standardsqliteexample.core.annotation.entity.Map_Ignore;
import com.example.standardsqliteexample.data.CookingDbHelper;
import com.example.standardsqliteexample.entity.Recipe;

public class RepositoryBase<T> {
	public CookingDbHelper dbHelper;
	private final Class<T> clazz;
	private final String TABLE_NAME;
	private final HashMap<String, String> fieldsMap;

	protected RepositoryBase(Context context, Class<T> clazz) {
		dbHelper = CookingDbHelper.getInstance(context);
		this.clazz = clazz;
		this.TABLE_NAME = clazz.getAnnotation(Map_Entity.class).name();
		fieldsMap = MapField(clazz);
		
		Log.d(null, this.TABLE_NAME);
	}
	private HashMap<String, String> MapField(Class<T> clazz){
		HashMap<String, String> fieldsMap = new HashMap<String, String>();
		Field[] fields = null;
		fields = clazz.getDeclaredFields();
		for(Field field : fields){
			String name = field.getName();
			Log.d("field", name);
			if(field.isAnnotationPresent(Map_Ignore.class)) continue;
			
			Log.d("field", name);
			String colTb = field.getAnnotation(Map_Entity_Column.class).name();
			
			fieldsMap.put(colTb, name);
		}
		
		Field[] superFields = clazz.getSuperclass().getDeclaredFields();
		
		for(Field field : superFields){
			String name = field.getName();
			Log.d("field", name);
			if(field.isAnnotationPresent(Map_Ignore.class)) continue;
			
			Log.d("field", name);
			String colTb = field.getAnnotation(Map_Entity_Column.class).name();
			
			fieldsMap.put(colTb, name);
		}
		
		return fieldsMap;
	}
	public Cursor rawQuery(String query, String[] selectionAgrs){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		Log.d("raw Query", query);
		Cursor c = db.rawQuery(query,selectionAgrs);
		
		return c;
	}
	public long add(T entity) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.putAll(getContentValues(entity));
		long newRowId  = -1;
		try{
			newRowId = db.insertOrThrow(this.TABLE_NAME, null, values);
		}catch (SQLiteConstraintException e){
			Log.d("Inserting error", e.getMessage());
			return -1;
		}
		db.close();
		return newRowId;

	}

	public void delete(String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.delete(TABLE_NAME, selection, selectionArgs);
	}

	public T getById(long id) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();

		Cursor c = db.query(this.TABLE_NAME, null, "_id = ?",
				new String[] { String.valueOf(id) }, null, null, null, null);

		if (c != null && c.getCount() > 0) {
			Log.d("", "vao cursor");
			c.moveToFirst();
			T instance = null;

			List<T> list = getListFromCursor(c);
			if (list != null && list.size() > 0) {
				instance = list.get(0);
				return instance;
			} else
				return null;

		} else
			return null;
	}

	public List<T> getBySelections(String[] projection, String selection,
			String[] selectionArgs, String groupBy, String having,
			String orderBy, String limit) {

		List<T> list = new ArrayList<T>();

		SQLiteDatabase db = dbHelper.getReadableDatabase();

		Cursor c = db.query(this.TABLE_NAME, projection, selection,
				selectionArgs, groupBy, having, orderBy, limit);
		// Cursor c = db.query(this.TABLE_NAME, null, null, null, null,
		// null,null, null);

		if (c.getCount() <= 0){
			Log.d("count","null");
			return null;
			
		}

		list = getListFromCursor(c);

		return list;
	}

	private ContentValues getContentValues(T clazz) {
		ContentValues values = new ContentValues();

		for (Field field : clazz.getClass().getDeclaredFields()) {
			
			//Check ignore mapping
			if(field.isAnnotationPresent(Map_Ignore.class)) continue;
			
			String fieldName = field.getName();
			String colName = field.getAnnotation(Map_Entity_Column.class).name();
			Class<?> fieldType = field.getType();

			Method getMethod = null;

			try {
				Log.d(null, "get" + Utilities.capitalizeFirstLetter(fieldName));

				getMethod = clazz.getClass().getMethod(
						"get" + Utilities.capitalizeFirstLetter(fieldName));

			} catch (NoSuchMethodException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Object methodReturn = null;
			try {
				methodReturn = getMethod.invoke(clazz);
				Log.d(null, String.valueOf(methodReturn));
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

			String fieldTypeName = fieldType.getSimpleName().toLowerCase(
					Locale.getDefault());

			if (fieldTypeName.equalsIgnoreCase("boolean")) {
				values.put(colName, (Boolean) methodReturn);
			}

			if (fieldTypeName.equalsIgnoreCase("byte")) {
				values.put(colName, (Byte) methodReturn);
			}

			if (fieldTypeName.equalsIgnoreCase("integer")) {
				values.put(colName, (Integer) methodReturn);
			}

			if (fieldTypeName.equalsIgnoreCase("long")) {
				values.put(colName, (Long) methodReturn);
			}

			if (fieldTypeName.equalsIgnoreCase("short")) {
				values.put(colName, (Short) methodReturn);
			}

			if (fieldTypeName.equalsIgnoreCase("string")) {
				values.put(colName, (String) methodReturn);
			}

			if (fieldTypeName.equalsIgnoreCase("boolean")) {
				values.put(colName, (Boolean) methodReturn);
			}

			if (fieldTypeName.equalsIgnoreCase("double")) {
				values.put(colName, (Double) methodReturn);
			}

			if (fieldTypeName.equalsIgnoreCase("float")) {
				values.put(colName, (Float) methodReturn);
			}

			Log.d("add action", fieldTypeName + "---" + methodReturn + "----"
					+ values.getAsString(colName) + "---" + colName);
		}
		return values;
	}

	private List<T> getListFromCursor(Cursor c) {
		List<T> list = new ArrayList<T>();
		String[] projection = c.getColumnNames();
		T instance = null;
		Log.d("getListFromCursor", String.valueOf(c.getCount()));
		if (c.moveToFirst()) {
			Log.d("start getListFromCursor", "in loop");
			do {
				try {
					instance = clazz.newInstance();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				for (String col : projection) {
					Log.d("col", col);
					//if (col.equalsIgnoreCase("_id"))
					//	continue;
					
					Field field = null;
					String fieldName = fieldsMap.get(col);
					Log.d("field",fieldName);
					try {
						
						field = instance.getClass().getDeclaredField(fieldName);
						Log.d("field",fieldName);
					} catch (NoSuchFieldException e) {
						// TODO Auto-generated catch block
						Class<? super T> superClass = clazz.getSuperclass();
					      if (superClass == null) {
					    	  e.printStackTrace();
					        try {
								throw e;
							} catch (NoSuchFieldException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					      } else {
					        try {
								field = superClass.getDeclaredField(col);
							} catch (NoSuchFieldException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					      }
					}
					// field.setAccessible(true);

					Class<?> fieldType = field.getType();
					Log.d("fieldtype", fieldType.getName());
					String fieldTypeName = fieldType.getSimpleName()
							.toLowerCase(Locale.getDefault());
					
					Method setMethod = null;
					try {
						setMethod = clazz.getMethod(
								"set" + Utilities.capitalizeFirstLetter(fieldName),
								fieldType);
					} catch (NoSuchMethodException e1) {
						// TODO Auto-generated catch block
						Class<? super T> superClass = clazz.getSuperclass();
					      if (superClass == null) {
					    	  e1.printStackTrace();
								try {
									throw e1;
								} catch (NoSuchMethodException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					      } else {
					    	  try {
								setMethod = superClass.getMethod(
											"set" + Utilities.capitalizeFirstLetter(col),
											fieldType);
							} catch (NoSuchMethodException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					      }
					}
					Log.d(null, fieldTypeName);
					Method cursorMethod = null;
					try {
						cursorMethod = c
								.getClass()
								.getMethod(
										"get"
												+ Utilities
														.capitalizeFirstLetter(fieldTypeName),
										int.class);

						Log.d("get method cursor",
								"get"
										+ Utilities
												.capitalizeFirstLetter(fieldTypeName));
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Object cursorReturn;
					try {
						cursorReturn = cursorMethod.invoke(c,
								c.getColumnIndex(col));
						Log.d("get action", cursorReturn.toString());
						setMethod.invoke(instance, cursorReturn);
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
			} while (c.moveToNext());
		}

		return list;
	}
}
