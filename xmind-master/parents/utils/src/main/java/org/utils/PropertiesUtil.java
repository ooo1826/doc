package org.utils;

import java.util.ResourceBundle;

public class PropertiesUtil{
	
	private static ResourceBundle rb;
	
	static{
		rb=ResourceBundle.getBundle("config");
	}
	
	public static String getValue(final String key){
		return rb.getString(key);
	}
	
}
