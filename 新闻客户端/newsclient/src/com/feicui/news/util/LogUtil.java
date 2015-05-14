package com.feicui.news.util;

import android.util.Log;

import com.feicui.news.BuildConfig;

/**
 * 这是一个打印运行日志工具类
 * */
public class LogUtil {

	public static final String TAG = "newsClient";
	/** 调试日志开关 */
	public static final boolean Debug = BuildConfig.DEBUG && true;

	/** 调试 */
	public static void d(String msg) {

		if (Debug) {
			Log.d(TAG, msg);
		}
	}

	/** 警告 */
	public static void w(String msg) {

		if (Debug) {
			Log.w(TAG, msg);
		}
	}

	/** 信息 */
	public static void i(String msg) {

		if (Debug) {
			Log.i(TAG, msg);
		}
	}

	/** 错误 */
	public static void e(String msg) {

		if (Debug) {
			Log.d(TAG, msg);
		}

	}

}
