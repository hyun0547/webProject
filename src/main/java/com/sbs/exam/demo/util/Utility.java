package com.sbs.exam.demo.util;

public class Utility {

	public static boolean checkNull(String str) {
		return str == null || str.trim().isEmpty();
	}
	
	public static String f(String format, Object... args) {
		return String.format(format, args);
	}
}
