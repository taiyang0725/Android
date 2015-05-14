package com.feicui.news.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * ���ݿ���
 * */
public class NewsDbHelper extends SQLiteOpenHelper {

	/** �汾�� */
	public static final int DB_VERSION = 1;
	/** ���ݿ��� */
	public static final String DB_NAME = "news";
	/**
	 * ���ŷ���
	 * */
	/** ���ŷ������ */
	public static final String TABLE_NEWS_SORT = "news_sort";
	/** �ӷ���� */
	public static final String NEWS_SUBID = "subid";
	/** �ӷ����� */
	public static final String NEWS_SUBGROUP = "subgroup";
	/**
	 * �����б�
	 * */
	/** �����б���� */
	public static final String TABLE_NEWS_LIST = "news_list";
	/** ���ͱ�ʾ */
	public static final String NEWS_TYPE = "type";
	/** ���ű�� */
	public static final String NEWS_NID = "nid";
	/** ����ʱ��� */
	public static final String NEWS_STAMP = "stamp";
	/** ͼ��·�� */
	public static final String NEWS_ICON = "icon";
	/** ���ű��� */
	public static final String NEWS_TITLE = "title";
	/** ����ժҪ */
	public static final String NEWS_SUMMARY = "summary";
	/** �������� */
	public static final String NEWS_LINK = "link";

	/** �����ղ��б� */
	public static final String TABLE_NEWS_LOVE = "news_love";

	public NewsDbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		/** ���ŷ��� */
		String news_sort = "CREATE  TABLE " + TABLE_NEWS_SORT + "( "
				+ "_id integer PRIMARY KEY AUTOINCREMENT," + " subid varchar,"
				+ "subgroup varchar);";
		db.execSQL(news_sort);
		/** �����б� */
		String news_list = "CREATE  TABLE " + TABLE_NEWS_LIST + "( "
				+ "_id integer PRIMARY KEY AUTOINCREMENT," + " type varchar,"
				+ " nid varchar," + "stamp varchar," + "icon varchar,"
				+ "title varchar," + "summary varchar," + "link varchar);";
		db.execSQL(news_list);
		/** �����ղ� */
		String news_love = "CREATE  TABLE " + TABLE_NEWS_LOVE + "( "
				+ "_id integer PRIMARY KEY AUTOINCREMENT," + " type varchar,"
				+ " nid varchar," + "stamp varchar," + "icon varchar,"
				+ "title varchar," + "summary varchar," + "link varchar);";
		db.execSQL(news_love);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
