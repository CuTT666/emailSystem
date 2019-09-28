package com.team1.utils;


import java.util.UUID;


public class PrimaryKeyUtil {

	public static String getPrimaryKey() {
		return UUID.randomUUID().toString();
	}
}
