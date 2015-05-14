package com.feicui.news.util;

import android.util.Log;

import com.feicui.news.BuildConfig;

/**
 * ����һ����ӡ������־������
 * */
public class LogUtil {

	public static final String TAG = "newsClient";
	/** ������־���� */
	public static final boolean Debug = BuildConfig.DEBUG && true;

	/** ���� */
	public static void d(String msg) {

		if (Debug) {
			Log.d(TAG, msg);
		}
	}

	/** ���� */
	public static void w(String msg) {

		if (Debug) {
			Log.w(TAG, msg);
		}
	}

	/** ��Ϣ */
	public static void i(String msg) {

		if (Debug) {
			Log.i(TAG, msg);
		}
	}

	/** ���� */
	public static void e(String msg) {

		if (Debug) {
			Log.d(TAG, msg);
		}

	}

}
