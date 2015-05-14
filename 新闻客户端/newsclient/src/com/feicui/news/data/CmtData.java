package com.feicui.news.data;

import java.util.ArrayList;

import org.apache.http.Header;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.feicui.news.manager.CmtManager;
import com.feicui.news.mode.BaseNews;
import com.feicui.news.mode.CmtCommit;
import com.feicui.news.mode.CmtList;
import com.feicui.news.mode.CmtNum;
import com.feicui.news.util.LogUtil;
import com.feicui.news.util.SystemUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;

/** 评论信息的类 */
public class CmtData {
	/** 发布评论 */
	public static void getCmtCommit(final Context context,
			final Handler handler, String nid, String token, String ctx) {
		CmtManager manager = new CmtManager();

		manager.cmtCommit(context, nid, token, ctx,
				new TextHttpResponseHandler() {

					@Override
					public void onSuccess(int arg0, Header[] arg1, String arg2) {
						Gson gson = new Gson();
						LogUtil.d(arg2);
						BaseNews<CmtCommit> baseNews = gson.fromJson(arg2,
								new TypeToken<BaseNews<CmtCommit>>() {
								}.getType());
						if (baseNews.getStatus().equals("0")) {

							CmtCommit commit = baseNews.getData();
							handler.sendEmptyMessage(1);
							Toast.makeText(context, commit.getExplain(),
									Toast.LENGTH_SHORT).show();

						}

					}

					@Override
					public void onFailure(int arg0, Header[] arg1, String arg2,
							Throwable arg3) {
						handler.sendEmptyMessage(2);
					}
				});

	}

	/** 显示评论的方法 */

	public static void getCmtList(final Context context, final Handler handler,
			String nid, String cid) {
		final ArrayList<CmtList> list = new ArrayList<CmtList>();

		CmtManager manager = new CmtManager();
		manager.cmtList(context, nid, SystemUtil.getTime(), cid, "0", "20",
				new TextHttpResponseHandler() {

					@Override
					public void onSuccess(int arg0, Header[] arg1, String arg2) {
						LogUtil.d("arg2" + arg2);

						Gson gson = new Gson();
						BaseNews<ArrayList<CmtList>> baseNews = gson.fromJson(
								arg2,
								new TypeToken<BaseNews<ArrayList<CmtList>>>() {
								}.getType());

						if (baseNews.getStatus().equals("0")) {
							ArrayList<CmtList> cmtLists = baseNews.getData();
							for (int i = 0; i < cmtLists.size(); i++) {
								list.add(cmtLists.get(i));

							}
							Message message = new Message();
							message.obj = list;
							message.what = 1;
							handler.sendMessage(message);
						} else {
							Toast.makeText(context, arg2, Toast.LENGTH_SHORT)
									.show();
							handler.sendEmptyMessage(3);
						}

					}

					@Override
					public void onFailure(int arg0, Header[] arg1, String arg2,
							Throwable arg3) {
						handler.sendEmptyMessage(2);
					}
				});

	}

	/** 评论数量 */
	public static void getCmtNum(Context context, String nid) {
		CmtManager manager = new CmtManager();
		manager.cmtNum(context, nid, new TextHttpResponseHandler() {

			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				Gson gson = new Gson();
				BaseNews<CmtNum> baseNews = gson.fromJson(arg2,
						new TypeToken<BaseNews<CmtNum>>() {
						}.getType());
				if (baseNews.getStatus().equals("0")) {

				}

			}

			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2,
					Throwable arg3) {

			}
		});

	}

}
