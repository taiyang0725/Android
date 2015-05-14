package com.feicui.news.util;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;

/**
 * ���ع�����
 * */
public class DownLoadUtil {
	/**
	 * ���ص�ID
	 * */
	public static long downLoad(Context context, String path) {
		/**
		 * ���ع�����
		 * */
		DownloadManager manager = (DownloadManager) context
				.getSystemService(Context.DOWNLOAD_SERVICE);

		Uri uri = Uri.parse(path);
		/**
		 * ����
		 * */
		DownloadManager.Request request = new DownloadManager.Request(uri);
		/**
		 * ֻ����WiFi����
		 * */
		request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
		/**
		 * ��������·��
		 * */
		request.setDestinationInExternalFilesDir(context, null,
				System.currentTimeMillis() + ".apk");

		return manager.enqueue(request);
	}

}
