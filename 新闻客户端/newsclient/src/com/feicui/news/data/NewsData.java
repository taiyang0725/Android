package com.feicui.news.data;

import java.util.ArrayList;

import org.apache.http.Header;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.feicui.news.db.NewsDbHelper;
import com.feicui.news.manager.NewsManager;
import com.feicui.news.mode.BaseNews;
import com.feicui.news.mode.NewsImage;
import com.feicui.news.mode.NewsList;
import com.feicui.news.mode.NewsSort;
import com.feicui.news.mode.SubGrp;
import com.feicui.news.util.NewsDbUtil;
import com.feicui.news.util.SystemUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;

/** 新闻数据类 */
public class NewsData {

	/** 获取新闻分类 */

	public static void getNewsTitle(final Context context, final Handler handler) {
		final ArrayList<SubGrp> list = new ArrayList<SubGrp>();
		NewsManager newsManager = new NewsManager();
		newsManager.getNewsSort(context, new TextHttpResponseHandler() {

			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				Gson gson = new Gson();

				BaseNews<ArrayList<NewsSort>> baseNews = gson.fromJson(arg2,
						new TypeToken<BaseNews<ArrayList<NewsSort>>>() {
						}.getType());
				if (baseNews.getStatus().equals("-3")) {
					handler.sendEmptyMessage(2);
				}

				if (baseNews.getStatus().equals("0")) {

					NewsSort newsSort = baseNews.getData().get(0);

					NewsDbUtil dbUtil = new NewsDbUtil(context);
					dbUtil.delAll(NewsDbHelper.TABLE_NEWS_SORT);

					ArrayList<SubGrp> subgrp = newsSort.getSubgrp();

					for (int i = 0; i < subgrp.size(); i++) {

						dbUtil.insert(subgrp.get(i));

						list.add(subgrp.get(i));

					}
					Message message = new Message();
					message.what = 1;
					message.obj = list;
					handler.sendMessage(message);

				}

			}

			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2,
					Throwable arg3) {

				handler.sendEmptyMessage(2);
			}
		});

	}

	/**
	 * 获取新闻列表
	 * */
	public static void getNewsLists(final Context context, String dir,
			String nid, final Handler handler) {

		final ArrayList<NewsList> list = new ArrayList<NewsList>();

		NewsManager newsManager = new NewsManager();
		newsManager.getNewsList(context, "1", dir, nid, SystemUtil.getTime(),
				"20", new TextHttpResponseHandler() {

					@Override
					public void onSuccess(int arg0, Header[] arg1, String arg2) {
						Gson gson = new Gson();

						BaseNews<ArrayList<NewsList>> baseNews = gson.fromJson(
								arg2,
								new TypeToken<BaseNews<ArrayList<NewsList>>>() {
								}.getType());

						if (baseNews.getStatus().equals("0")) {
							ArrayList<NewsList> newsLists = baseNews.getData();

							NewsDbUtil dbUtil = new NewsDbUtil(context);
							dbUtil.delAll(NewsDbHelper.TABLE_NEWS_LIST);

							for (int i = 0; i < newsLists.size(); i++) {

								dbUtil.insert(newsLists.get(i));

								list.add(newsLists.get(i));

							}
						}
						Message message = new Message();
						message.what = 1;
						message.obj = list;
						handler.sendMessage(message);

					}

					@Override
					public void onFailure(int arg0, Header[] arg1, String arg2,
							Throwable arg3) {
						handler.sendEmptyMessage(2);
					}

				});

	}

	/**
	 * 获取新闻内容
	 * */
	public static void getNewsImage(final Context context, final Handler handler) {
		final ArrayList<NewsImage> list = new ArrayList<NewsImage>();

		NewsManager newsManager = new NewsManager();
		newsManager.getNewsImage(context, "1", new TextHttpResponseHandler() {

			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {

				Gson gson = new Gson();
				BaseNews<ArrayList<NewsImage>> baseNews = gson.fromJson(arg2,
						new TypeToken<BaseNews<ArrayList<NewsImage>>>() {
						}.getType());
				if (baseNews.getStatus().equals("0")) {

					ArrayList<NewsImage> newsImages = baseNews.getData();
					for (int i = 0; i < newsImages.size(); i++) {

						list.add(newsImages.get(i));

					}
					Message message = new Message();
					message.what = 1;
					message.obj = list;
					handler.sendMessage(message);

				}

			}

			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2,
					Throwable arg3) {
				handler.sendEmptyMessage(2);
			}
		});

	}
}
