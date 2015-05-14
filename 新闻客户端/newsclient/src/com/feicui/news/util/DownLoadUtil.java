package com.feicui.news.util;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;

/**
 * 下载工具类
 * */
public class DownLoadUtil {
	/**
	 * 下载的ID
	 * */
	public static long downLoad(Context context, String path) {
		/**
		 * 下载管理者
		 * */
		DownloadManager manager = (DownloadManager) context
				.getSystemService(Context.DOWNLOAD_SERVICE);

		Uri uri = Uri.parse(path);
		/**
		 * 请求
		 * */
		DownloadManager.Request request = new DownloadManager.Request(uri);
		/**
		 * 只允许WiFi下载
		 * */
		request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
		/**
		 * 设置下载路径
		 * */
		request.setDestinationInExternalFilesDir(context, null,
				System.currentTimeMillis() + ".apk");

		return manager.enqueue(request);
	}

}
