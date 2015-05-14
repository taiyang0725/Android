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
 * 加载图片的工具类
 * */
public class ImageLoader {

	private Context context;

	private OnImageLoadListener onImageLoad;

	public ImageLoader(Context context) {
		super();
		this.context = context;

	}

	/** 定义接口 */
	public interface OnImageLoadListener {
		/** 图片加载回调方法 */
		void onImage(Bitmap bitmap, String path);

	}

	public void setOnImageLoadListener(OnImageLoadListener onImageLoad) {
		this.onImageLoad = onImageLoad;

	}

	// 获取到可用内存的最大值，使用内存超出这个值会引起OutOfMemory异常。
	// LruCache通过构造函数传入缓存值，以KB为单位。
	private int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
	// 使用最大可用内存值的1/8作为缓存的大小。
	private int cacheSize = maxMemory / 8;

	/** 内存缓存软引用 */
	private LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(
			cacheSize);

	/** 获取图片 */
	public Bitmap getImage(String path) {
		Bitmap bitmap = null;

		if (TextUtils.isEmpty(path)) {
			return bitmap;
		}
		/** 内存中取 */
		bitmap = imgFromMem(path);
		if (bitmap != null) {
			return bitmap;
		}

		/** 缓存文件中取 */

		bitmap = imgFromFile(path);
		if (bitmap != null) {
			/** 存入内存 */
			cache.put(path, bitmap);

			return bitmap;
		}
		/** 网络中取 */
		imgFromNet(path);
		return bitmap;

	}

	/**
	 * 图片获取来自内存
	 * */
	private Bitmap imgFromMem(String path) {
		return cache.get(path);
	}

	/**
	 * 图片获取来自本地
	 * */
	private Bitmap imgFromFile(String path) {

		String name = MD5.getMD5Str(path);
		/** 获取缓存文件目录 */
		File cacheDir = context.getCacheDir();
		if (cacheDir == null) {
			return null;
		}
		/** 获取目录中的文件 */
		File[] files = cacheDir.listFiles();
		if (files == null) {
			return null;
		}
		/** 图片文件 */
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
	 * 图片获取来自网络
	 * */
	private void imgFromNet(String path) {
		Download download = new Download();
		download.execute(path);

	}

	/** 异步工具类网络下载图片类 */
	class Download extends AsyncTask<String, Integer, Bitmap> {
		/** 图片输出流 */
		InputStream stream;
		/** 图片输入流 */
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

				/** 存入内存 */
				cache.put(params[0], bitmap);
				/** 存入缓存文件 */
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

		/** 存入缓存文件的方法 */
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
