package com.feicui.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;

/**
 * ����Fragment
 * */
public class BaseFragment extends Fragment {
	/**
	 * ����
	 * */
	protected int screenW;
	/**
	 * ����
	 * */
	protected int screenH;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		initScreen();
	}

	/**
	 * ��ȡ��Ļ��С
	 * */
	private void initScreen() {

		DisplayMetrics dm = new DisplayMetrics();

		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

		screenW = dm.widthPixels;
		screenH = dm.heightPixels;

	}
}
