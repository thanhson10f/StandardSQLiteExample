package com.example.standardsqliteexample.data;

public class DbConfig {
	public final static String packageTb = "com.example.standardsqliteexample.data.CookingContract";
	public static String getPackageTbName(String modelName){
		return packageTb + modelName + "Tb";
	}
}
