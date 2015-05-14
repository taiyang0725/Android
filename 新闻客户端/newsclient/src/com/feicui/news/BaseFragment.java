package com.feicui.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;

/**
 * 基础Fragment
 * */
public class BaseFragment extends Fragment {
	/**
	 * 屏宽
	 * */
	protected int screenW;
	/**
	 * 屏高
	 * */
	protected int screenH;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		initScreen();
	}

	/**
	 * 获取屏幕大小
	 * */
	private void initScreen() {

		DisplayMetrics dm = new DisplayMetrics();

		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

		screenW = dm.widthPixels;
		screenH = dm.heightPixels;

	}
}
