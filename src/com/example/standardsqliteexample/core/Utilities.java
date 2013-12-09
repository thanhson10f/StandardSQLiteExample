package com.example.standardsqliteexample.core;

import java.lang.reflect.Field;
import java.util.Locale;

import android.util.Log;

import com.example.standardsqliteexample.core.annotation.entity.Map_Entity;
import com.example.standardsqliteexample.core.annotation.entity.Map_Entity_Column;
import com.example.standardsqliteexample.core.annotation.entity.Map_Ignore;

public class Utilities {
	public static String capitalizeFirstLetter(String original){
	    if(original.length() == 0)
	        return original;
	    return original.substring(0, 1).toUpperCase(Locale.getDefault()) + original.substring(1);
	}
	
	public static String GenerateCREATESQLQuery(Class<?> clazz){
		
		if(!clazz.isAnnotationPresent(Map_Entity.class)) return null;
		Map_Entity entityAnn = clazz.getAnnotation(Map_Entity.class);
		
		boolean isDefinedPK = false;
if(entityAnn.primaryKey().length>0)isDefinedPK=true;
		
		String query = "";
		query +="CREATE TABLE " + entityAnn.name();
		query +="(";
		
		Field[] superField = clazz.getSuperclass().getDeclaredFields();
		Field[] ownFields = clazz.getDeclaredFields();
		Field[] fields = new Field[superField.length+ownFields.length];
		System.arraycopy( superField, 0, fields, 0, superField.length);
		System.arraycopy( ownFields, 0, fields, superField.length, ownFields.length );
		
		Class<?> fieldType = null;
		for(Field field : fields){
			
			if(field.isAnnotationPresent(Map_Ignore.class)) continue;
			
			if(field.isAnnotationPresent(Map_Entity_Column.class)){
				fieldType= field.getType();
				Map_Entity_Column colAnn = field.getAnnotation(Map_Entity_Column.class);
				
				Log.d("log", field.getName()+ "---"+colAnn.getClass().getName());
				query+=colAnn.name()+" ";
				
				String type = colAnn.type();
				if(type.equalsIgnoreCase(""))
					type = getTypeSQL(fieldType,colAnn.length());
				
				query+=type+" ";
				if(colAnn.isPrimaryKey() && !isDefinedPK)
					query+="PRIMARY KEY"+" ";
				if(!colAnn.allowNull())
					query+="NOT NULL"+" ";
				if(colAnn.isUnique())
					query+="UNIQUE"+" ";
				if(!colAnn.FK().equalsIgnoreCase(""))
					query+="REFERENCES "+colAnn.FK()+" ";
				query+=",";
					
			}
			
		}
		if(isDefinedPK) {
			query+="PRIMARY KEY("+strJoin(entityAnn.primaryKey(),",")+"),";
		}
		//bo dau phay cuoi cung
		query = query.trim().substring(0, query.length()-1);
		query +=")";
		Log.d("SQL", query);
		return query;
	}
	
	public static String getTypeSQL(Class<?> fieldType, int length){
		String type = null;
			if(fieldType.equals(Integer.class) || fieldType.equals(int.class)){
				return "INTEGER";
			}
			if(fieldType.equals(Long.class) || fieldType.equals(long.class)){
				return "INTEGER";
			}
			if(fieldType.equals(Short.class) || fieldType.equals(short.class)){
				return "INTEGER";
			}
			if(fieldType.equals(Float.class) || fieldType.equals(float.class)){
				return "REAL";
			}
			if(fieldType.equals(Double.class) || fieldType.equals(double.class)){
				return "REAL";
			}
			if(fieldType.equals(Boolean.class) || fieldType.equals(boolean.class)){
				return "NUMERIC";
			}
			if(fieldType.equals(String.class)){
				if(length==-1)
					return "TEXT";
				else return "TEXT("+length+")";
			}
			
		return type;
	}
	
	public static String strJoin(String[] aArr, String sSep) {
	    StringBuilder sbStr = new StringBuilder();
	    for (int i = 0, il = aArr.length; i < il; i++) {
	        if (i > 0)
	            sbStr.append(sSep);
	        sbStr.append(aArr[i]);
	    }
	    return sbStr.toString();
	}
}
