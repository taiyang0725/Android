package com.feicui.news.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * �ж������Ƿ����ӵ���
 * */
public class NetworkUtil {

	public static boolean isConnected(Context context) {

		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo networkInfo = manager.getActiveNetworkInfo();

		return (networkInfo != null)
				&& (networkInfo.getType() == ConnectivityManager.TYPE_WIFI)
				&& (networkInfo.isConnected());

	}

}
