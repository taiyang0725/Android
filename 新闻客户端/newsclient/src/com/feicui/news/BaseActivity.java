package com.feicui.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.Window;

/**
 * ������
 * */
public class BaseActivity extends FragmentActivity {

	/**
	 * ����
	 * */
	protected int screenW;
	/**
	 * ����
	 * */
	protected int screenH;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setFullScreen();

		initScreen();
	}

	/**
	 * ����ȫ���ķ���
	 * */
	private void setFullScreen() {

		/**
		 * ���ñ�����Ϊ���صķ���
		 * */
		requestWindowFeature(Window.FEATURE_NO_TITLE);

	}

	/**
	 * ��ȡ��Ļ��С
	 * */
	private void initScreen() {

		DisplayMetrics dm = new DisplayMetrics();

		getWindowManager().getDefaultDisplay().getMetrics(dm);

		screenW = dm.widthPixels;
		screenH = dm.heightPixels;

	}

	/**
	 * Fragment�滻�ķ���
	 * */
	public void replace(int resId, Fragment fragment) {
		getSupportFragmentManager().beginTransaction().replace(resId, fragment)
				.commit();
	}

	/**
	 * Fragment�滻�ķ���
	 * */
	public void replace(Fragment fragment) {
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.framelayout, fragment).commit();
	}

	/**
	 * Fragment���ӵķ���
	 * */
	public void add(Fragment fragment) {
		getSupportFragmentManager().beginTransaction()
				.add(R.id.framelayout, fragment).commit();
	}

}
