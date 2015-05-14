package com.feicui.news.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.telephony.TelephonyManager;

import com.feicui.news.R;

/**
 * ����һ����ȡϵͳ��Ϣ�Ĺ�����
 * */
public class SystemUtil {
	/**
	 * ��ȡ����汾�ŵķ���
	 * */
	public static String getVersionName(Context context) {

		PackageManager manager = context.getPackageManager();

		try {
			PackageInfo packageInfo = manager.getPackageInfo(
					context.getPackageName(), 0);

			return packageInfo.versionName;

		} catch (NameNotFoundException e) {

			e.printStackTrace();
			return context.getString(R.string.not_find_version);
		}

	}

	/**
	 * ��ȡ����汾�ŵķ���
	 * */
	public static int getVersionCode(Context context) {

		PackageManager manager = context.getPackageManager();

		PackageInfo packageInfo = null;
		try {
			packageInfo = manager.getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return packageInfo.versionCode;

	}

	/**
	 * ��ȡ����汾�ŵķ���(ȥ����İ汾��)
	 * */
	public static String getVersionName0(Context context) {
		String code = getVersionName(context);
		String code1 = "";
		String[] num = code.split("[.]");
		for (int i = 0; i < num.length; i++) {
			code1 += num[i];
		}
		return code1;

	}

	/**
	 * ��ȡ�ֻ���ʾ������
	 * */
	public static String getImei(Context context) {
		TelephonyManager tManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return tManager.getDeviceId();
	}

	/**
	 * ��ȡ��ǰʱ��ʱ��
	 * */
	public static String getTime() {

		long time = System.currentTimeMillis();
		String format = "%1$tY-%1$tm-%1$td %1$tA %1$tH:%1$tM";
		return String.format(format, time);

	}

}
