package com.feicui.news.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.text.TextUtils;

/**
 * ����ͼƬ�Ĺ�����
 * */
public class ImageLoader {

	private Context context;

	private OnImageLoadListener onImageLoad;

	public ImageLoader(Context context) {
		super();
		this.context = context;

	}

	/** ����ӿ� */
	public interface OnImageLoadListener {
		/** ͼƬ���ػص����� */
		void onImage(Bitmap bitmap, String path);

	}

	public void setOnImageLoadListener(OnImageLoadListener onImageLoad) {
		this.onImageLoad = onImageLoad;

	}

	// ��ȡ�������ڴ�����ֵ��ʹ���ڴ泬�����ֵ������OutOfMemory�쳣��
	// LruCacheͨ�����캯�����뻺��ֵ����KBΪ��λ��
	private int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
	// ʹ���������ڴ�ֵ��1/8��Ϊ����Ĵ�С��
	private int cacheSize = maxMemory / 8;

	/** �ڴ滺�������� */
	private LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(
			cacheSize);

	/** ��ȡͼƬ */
	public Bitmap getImage(String path) {
		Bitmap bitmap = null;

		if (TextUtils.isEmpty(path)) {
			return bitmap;
		}
		/** �ڴ���ȡ */
		bitmap = imgFromMem(path);
		if (bitmap != null) {
			return bitmap;
		}

		/** �����ļ���ȡ */

		bitmap = imgFromFile(path);
		if (bitmap != null) {
			/** �����ڴ� */
			cache.put(path, bitmap);

			return bitmap;
		}
		/** ������ȡ */
		imgFromNet(path);
		return bitmap;

	}

	/**
	 * ͼƬ��ȡ�����ڴ�
	 * */
	private Bitmap imgFromMem(String path) {
		return cache.get(path);
	}

	/**
	 * ͼƬ��ȡ���Ա���
	 * */
	private Bitmap imgFromFile(String path) {

		String name = MD5.getMD5Str(path);
		/** ��ȡ�����ļ�Ŀ¼ */
		File cacheDir = context.getCacheDir();
		if (cacheDir == null) {
			return null;
		}
		/** ��ȡĿ¼�е��ļ� */
		File[] files = cacheDir.listFiles();
		if (files == null) {
			return null;
		}
		/** ͼƬ�ļ� */
		File bitmapFile = null;

		for (File file : files) {
			if (file.getName().equals(name)) {
				bitmapFile = file;
				break;
			}
		}
		if (bitmapFile == null) {
			return null;
		}
		Bitmap bitmap = BitmapFactory.decodeFile(bitmapFile.getAbsolutePath());
		if (bitmap == null) {
			return null;
		}

		return bitmap;
	}

	/**
	 * ͼƬ��ȡ��������
	 * */
	private void imgFromNet(String path) {
		Download download = new Download();
		download.execute(path);

	}

	/** �첽��������������ͼƬ�� */
	class Download extends AsyncTask<String, Integer, Bitmap> {
		/** ͼƬ����� */
		InputStream stream;
		/** ͼƬ������ */
		OutputStream outputStream;
		String path;

		@Override
		protected Bitmap doInBackground(String... params) {
			Bitmap bitmap = null;
			try {
				path = params[0];
				URL url = new URL(path);

				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection();

				stream = connection.getInputStream();

				bitmap = BitmapFactory.decodeStream(stream);
				if (bitmap == null) {
					return null;
				}

				/** �����ڴ� */
				cache.put(params[0], bitmap);
				/** ���뻺���ļ� */
				saveCacheFile(params[0], bitmap);
			} catch (MalformedURLException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			} finally {
				if (stream != null) {
					try {
						stream.close();
					} catch (IOException e) {

						e.printStackTrace();
					}
				}
			}

			return bitmap;
		}

		/** ���뻺���ļ��ķ��� */
		private void saveCacheFile(String path, Bitmap bitmap) {
			String name = MD5.getMD5Str(path);
			File cacheDir = context.getCacheDir();
			if (!cacheDir.exists()) {
				cacheDir.mkdirs();
			}
			try {
				outputStream = new FileOutputStream(new File(cacheDir, name));
				bitmap.compress(CompressFormat.PNG, 80, outputStream);
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			} finally {
				if (outputStream != null) {
					try {
						outputStream.close();
					} catch (IOException e) {

						e.printStackTrace();
					}
				}
			}

		}

		@Override
		protected void onProgressUpdate(Integer... values) {

			super.onProgressUpdate(values);
		}

		@Override
		protected void onPostExecute(Bitmap bitmap) {

			super.onPostExecute(bitmap);
			if (onImageLoad != null) {
				onImageLoad.onImage(bitmap, path);
			}

		}
	}

}
