package com.feicui.news.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库类
 * */
public class NewsDbHelper extends SQLiteOpenHelper {

	/** 版本号 */
	public static final int DB_VERSION = 1;
	/** 数据库名 */
	public static final String DB_NAME = "news";
	/**
	 * 新闻分类
	 * */
	/** 新闻分类表名 */
	public static final String TABLE_NEWS_SORT = "news_sort";
	/** 子分类号 */
	public static final String NEWS_SUBID = "subid";
	/** 子分类名 */
	public static final String NEWS_SUBGROUP = "subgroup";
	/**
	 * 新闻列表
	 * */
	/** 新闻列表表名 */
	public static final String TABLE_NEWS_LIST = "news_list";
	/** 类型标示 */
	public static final String NEWS_TYPE = "type";
	/** 新闻编号 */
	public static final String NEWS_NID = "nid";
	/** 新闻时间戳 */
	public static final String NEWS_STAMP = "stamp";
	/** 图标路径 */
	public static final String NEWS_ICON = "icon";
	/** 新闻标题 */
	public static final String NEWS_TITLE = "title";
	/** 新闻摘要 */
	public static final String NEWS_SUMMARY = "summary";
	/** 新闻链接 */
	public static final String NEWS_LINK = "link";

	/** 新闻收藏列表 */
	public static final String TABLE_NEWS_LOVE = "news_love";

	public NewsDbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		/** 新闻分类 */
		String news_sort = "CREATE  TABLE " + TABLE_NEWS_SORT + "( "
				+ "_id integer PRIMARY KEY AUTOINCREMENT," + " subid varchar,"
				+ "subgroup varchar);";
		db.execSQL(news_sort);
		/** 新闻列表 */
		String news_list = "CREATE  TABLE " + TABLE_NEWS_LIST + "( "
				+ "_id integer PRIMARY KEY AUTOINCREMENT," + " type varchar,"
				+ " nid varchar," + "stamp varchar," + "icon varchar,"
				+ "title varchar," + "summary varchar," + "link varchar);";
		db.execSQL(news_list);
		/** 新闻收藏 */
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
