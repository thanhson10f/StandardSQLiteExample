package com.example.standardsqliteexample.core;

import java.util.Locale;

public class Utilities {
	public static String capitalizeFirstLetter(String original){
	    if(original.length() == 0)
	        return original;
	    return original.substring(0, 1).toUpperCase(Locale.getDefault()) + original.substring(1);
	}
}
