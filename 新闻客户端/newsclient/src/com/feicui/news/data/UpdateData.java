package com.feicui.news.data;

import org.apache.http.Header;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.feicui.news.config.NetConf;
import com.feicui.news.mode.Update;
import com.feicui.news.util.LogUtil;
import com.feicui.news.util.SystemUtil;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

/** °æ±¾Éý¼¶ */
public class UpdateData {

	private static Update update;

	public static void setUpdate(Context context, final Handler handler) {

		AsyncHttpClient client = new AsyncHttpClient();

		StringBuffer url = new StringBuffer(NetConf.UPDATE);
		url.append("imei=").append(SystemUtil.getImei(context));
		url.append("&");
		url.append("pkg=").append(context.getPackageName());
		url.append("&");
		url.append("ver=").append(SystemUtil.getVersionCode(context));
		client.get(url.toString(), new TextHttpResponseHandler() {

			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				LogUtil.d(arg2);

				Gson gson = new Gson();

				update = gson.fromJson(arg2, Update.class);

				Message message = new Message();
				message.obj = update;
				message.what = 1;
				handler.sendMessage(message);

			}

			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2,
					Throwable arg3) {
				handler.sendEmptyMessage(2);

			}
		});

	}

}
