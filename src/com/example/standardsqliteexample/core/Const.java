package com.example.standardsqliteexample.core;

import java.util.ArrayList;

public class Const {
	public static ArrayList<String> getTypeNameLower(){
		ArrayList<String> list =  new ArrayList<String>();
		list.add("string");
		list.add("integer");
		list.add("long");
		list.add("boolean");
		list.add("byte");
		list.add("double");
		list.add("float");
		list.add("short");
		
		return list;
	}
}
