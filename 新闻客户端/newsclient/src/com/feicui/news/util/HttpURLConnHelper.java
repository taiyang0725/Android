package com.feicui.news.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

/** ���������lei */
public class HttpURLConnHelper {
	private final static String TAG = "MyHttpHelperUtil";

	/**
	 * ���ã�ʵ����������ļ�������ȡ�������ݴ����ֽ�������
	 * 
	 * @param url
	 *            �����������url��ַ
	 * @return byte[]
	 */
	public static byte[] downLoadByteFromURL(String url) {
		Log.i(TAG, "==��get��ʽ��������");
		HttpURLConnection httpConn = null;
		BufferedInputStream bis = null;
		ByteArrayOutputStream baos = null;
		try {
			Log.i(TAG, "==1������URL����url-->" + url);
			URL urlObj = new URL(url);
			Log.i(TAG, "==2���������ӣ�openConnection");
			httpConn = (HttpURLConnection) urlObj.openConnection();
			// ���������Ĭ��ֵ����Ȼ���Բ�д�����ǽ���д�ϡ�
			httpConn.setRequestMethod("GET");
			// // ���ý���������������д�뵽httpConn����
			// httpConn.setDoInput(true);
			// ���÷��ʳ�ʱʱ��
			httpConn.setConnectTimeout(1000);
			// // �����Ƿ�ʹ�û���
			// httpConn.setUseCaches(false);
			// // ����Զ�̶���ʵ������
			// httpConn.connect();
			Log.i(TAG,
					"==3��getResponseCode()������ȡ�������ķ�����:"
							+ httpConn.getResponseCode());
			if (httpConn.getResponseCode() == 200) {
				Log.i(TAG,
						"==4������ HttpURLConnection�����getInputStream()������ȡ���������ص�����Ϣ");
				bis = new BufferedInputStream(httpConn.getInputStream());
				Log.i(TAG, "==5��ִ�б�׼��IO������");
				baos = new ByteArrayOutputStream();
				int c = 0;
				byte[] buffer = new byte[8 * 1024];
				while ((c = bis.read(buffer)) != -1) {
					baos.write(buffer, 0, c);
					baos.flush();
				}
				byte[] result = baos.toByteArray();
				Log.i(TAG, "result==" + result.length);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if (baos != null) {
					baos.close();
				}
				if (httpConn != null) {
					httpConn.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
