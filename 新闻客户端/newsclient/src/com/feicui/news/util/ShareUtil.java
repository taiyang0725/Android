package com.feicui.news.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * ����������
 * */
public class ShareUtil {

	public static final String NAME = "name";

	/**
	 * ��������
	 * */
	public static void setOnclick(Context context, boolean isOnclick, String key) {

		SharedPreferences preferences = context.getSharedPreferences(NAME,
				Context.MODE_PRIVATE);

		Editor editor = preferences.edit();
		editor.putBoolean(key, isOnclick);
		editor.commit();

	}

	/**
	 * ȡ������
	 * */
	public static boolean isOnclick(Context context, String key) {

		SharedPreferences preferences = context.getSharedPreferences(NAME,
				Context.MODE_PRIVATE);

		return preferences.getBoolean(key, false);
	}

	/**
	 * ��������
	 * */
	public static void setShare(Context context, String info, String key) {

		SharedPreferences preferences = context.getSharedPreferences(NAME,
				Context.MODE_PRIVATE);

		Editor editor = preferences.edit();
		editor.putString(key, info);
		editor.commit();

	}

	/**
	 * ȡ������
	 * */
	public static String getShare(Context context, String key) {

		SharedPreferences preferences = context.getSharedPreferences(NAME,
				Context.MODE_PRIVATE);

		return preferences.getString(key, NAME);
	}
}
