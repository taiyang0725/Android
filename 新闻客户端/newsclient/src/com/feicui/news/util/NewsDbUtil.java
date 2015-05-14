package com.feicui.news.util;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.feicui.news.db.NewsDbHelper;
import com.feicui.news.mode.NewsList;
import com.feicui.news.mode.SubGrp;

/** 数据库工具类 */
public class NewsDbUtil {

	private NewsDbHelper newsDbHelper;

	public NewsDbUtil(Context context) {
		super();
		newsDbHelper = new NewsDbHelper(context);
	}

	/**
	 * 新闻分类insert
	 * */
	public void insert(SubGrp subGrp) {

		SQLiteDatabase database = newsDbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(NewsDbHelper.NEWS_SUBID, subGrp.getSubid());
		values.put(NewsDbHelper.NEWS_SUBGROUP, subGrp.getSubgroup());

		database.insert(NewsDbHelper.TABLE_NEWS_SORT, null, values);
		database.close();
	}

	/**
	 * 新闻列表insert
	 * */
	public void insert(NewsList newsList) {

		SQLiteDatabase database = newsDbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(NewsDbHelper.NEWS_TYPE, newsList.getType());
		values.put(NewsDbHelper.NEWS_NID, newsList.getNid());
		values.put(NewsDbHelper.NEWS_STAMP, newsList.getStamp());
		values.put(NewsDbHelper.NEWS_ICON, newsList.getIcon());
		values.put(NewsDbHelper.NEWS_TITLE, newsList.getTitle());
		values.put(NewsDbHelper.NEWS_SUMMARY, newsList.getSummary());
		values.put(NewsDbHelper.NEWS_LINK, newsList.getLink());

		database.insert(NewsDbHelper.TABLE_NEWS_LIST, null, values);
		database.close();
	}

	/**
	 * 新闻收藏列表insert
	 * */
	public void insertLove(NewsList newsList) {

		SQLiteDatabase database = newsDbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(NewsDbHelper.NEWS_TYPE, newsList.getType());
		values.put(NewsDbHelper.NEWS_NID, newsList.getNid());
		values.put(NewsDbHelper.NEWS_STAMP, newsList.getStamp());
		values.put(NewsDbHelper.NEWS_ICON, newsList.getIcon());
		values.put(NewsDbHelper.NEWS_TITLE, newsList.getTitle());
		values.put(NewsDbHelper.NEWS_SUMMARY, newsList.getSummary());
		values.put(NewsDbHelper.NEWS_LINK, newsList.getLink());

		database.insert(NewsDbHelper.TABLE_NEWS_LOVE, null, values);
		database.close();
	}

	/**
	 * 新闻分类query
	 * */
	public ArrayList<SubGrp> queryNewsSort() {

		ArrayList<SubGrp> list = new ArrayList<SubGrp>();

		SQLiteDatabase database = newsDbHelper.getReadableDatabase();

		Cursor cursor = database.query(NewsDbHelper.TABLE_NEWS_SORT, null,
				null, null, null, null, null);
		while (cursor.moveToNext()) {
			SubGrp grp = new SubGrp();

			grp.setSubid(cursor.getString(cursor
					.getColumnIndex(NewsDbHelper.NEWS_SUBID)));

			grp.setSubgroup(cursor.getString(cursor
					.getColumnIndex(NewsDbHelper.NEWS_SUBGROUP)));
			list.add(grp);
		}
		database.close();
		return list;

	}

	/**
	 * 新闻列表queryNewsList
	 * */
	public ArrayList<NewsList> queryNewsList() {

		ArrayList<NewsList> list = new ArrayList<NewsList>();

		SQLiteDatabase database = newsDbHelper.getReadableDatabase();

		Cursor cursor = database.query(NewsDbHelper.TABLE_NEWS_LIST, null,
				null, null, null, null, null);
		while (cursor.moveToNext()) {
			NewsList newsList = new NewsList();

			newsList.setIcon(cursor.getString(cursor
					.getColumnIndex(NewsDbHelper.NEWS_ICON)));

			newsList.setTitle(cursor.getString(cursor
					.getColumnIndex(NewsDbHelper.NEWS_TITLE)));

			newsList.setSummary(cursor.getString(cursor
					.getColumnIndex(NewsDbHelper.NEWS_SUMMARY)));

			newsList.setStamp(cursor.getString(cursor
					.getColumnIndex(NewsDbHelper.NEWS_STAMP)));

			list.add(newsList);

		}
		database.close();
		return list;

	}

	/**
	 * 新闻收藏列表queryNewsList
	 * */
	public ArrayList<NewsList> queryNewsListLove() {

		ArrayList<NewsList> list = new ArrayList<NewsList>();

		SQLiteDatabase database = newsDbHelper.getReadableDatabase();

		Cursor cursor = database.query(NewsDbHelper.TABLE_NEWS_LOVE, null,
				null, null, null, null, null);
		while (cursor.moveToNext()) {
			NewsList newsList = new NewsList();

			newsList.setIcon(cursor.getString(cursor
					.getColumnIndex(NewsDbHelper.NEWS_ICON)));

			newsList.setTitle(cursor.getString(cursor
					.getColumnIndex(NewsDbHelper.NEWS_TITLE)));

			newsList.setSummary(cursor.getString(cursor
					.getColumnIndex(NewsDbHelper.NEWS_SUMMARY)));

			newsList.setStamp(cursor.getString(cursor
					.getColumnIndex(NewsDbHelper.NEWS_STAMP)));

			list.add(newsList);

		}
		database.close();
		return list;

	}

	/**
	 * 清空数据
	 * */
	public void delAll(String table) {
		SQLiteDatabase database = newsDbHelper.getWritableDatabase();

		database.delete(table, null, null);

		database.close();

	}

	/**
	 * 清空指定数据
	 * */
	public void delete(String table, String nid) {
		SQLiteDatabase database = newsDbHelper.getWritableDatabase();

		database.delete(table, " nid=? ", new String[] { nid });

		database.close();

	}
}
