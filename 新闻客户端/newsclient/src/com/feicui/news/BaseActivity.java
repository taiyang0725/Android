package com.feicui.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.Window;

/**
 * 基础类
 * */
public class BaseActivity extends FragmentActivity {

	/**
	 * 屏宽
	 * */
	protected int screenW;
	/**
	 * 屏高
	 * */
	protected int screenH;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setFullScreen();

		initScreen();
	}

	/**
	 * 设置全屏的方法
	 * */
	private void setFullScreen() {

		/**
		 * 设置标题栏为隐藏的方法
		 * */
		requestWindowFeature(Window.FEATURE_NO_TITLE);

	}

	/**
	 * 获取屏幕大小
	 * */
	private void initScreen() {

		DisplayMetrics dm = new DisplayMetrics();

		getWindowManager().getDefaultDisplay().getMetrics(dm);

		screenW = dm.widthPixels;
		screenH = dm.heightPixels;

	}

	/**
	 * Fragment替换的方法
	 * */
	public void replace(int resId, Fragment fragment) {
		getSupportFragmentManager().beginTransaction().replace(resId, fragment)
				.commit();
	}

	/**
	 * Fragment替换的方法
	 * */
	public void replace(Fragment fragment) {
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.framelayout, fragment).commit();
	}

	/**
	 * Fragment增加的方法
	 * */
	public void add(Fragment fragment) {
		getSupportFragmentManager().beginTransaction()
				.add(R.id.framelayout, fragment).commit();
	}

}
