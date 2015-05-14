package com.feicui.news.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 共享数据类
 * */
public class ShareUtil {

	public static final String NAME = "name";

	/**
	 * 储存数据
	 * */
	public static void setOnclick(Context context, boolean isOnclick, String key) {

		SharedPreferences preferences = context.getSharedPreferences(NAME,
				Context.MODE_PRIVATE);

		Editor editor = preferences.edit();
		editor.putBoolean(key, isOnclick);
		editor.commit();

	}

	/**
	 * 取出数据
	 * */
	public static boolean isOnclick(Context context, String key) {

		SharedPreferences preferences = context.getSharedPreferences(NAME,
				Context.MODE_PRIVATE);

		return preferences.getBoolean(key, false);
	}

	/**
	 * 储存数据
	 * */
	public static void setShare(Context context, String info, String key) {

		SharedPreferences preferences = context.getSharedPreferences(NAME,
				Context.MODE_PRIVATE);

		Editor editor = preferences.edit();
		editor.putString(key, info);
		editor.commit();

	}

	/**
	 * 取出数据
	 * */
	public static String getShare(Context context, String key) {

		SharedPreferences preferences = context.getSharedPreferences(NAME,
				Context.MODE_PRIVATE);

		return preferences.getString(key, NAME);
	}
}
