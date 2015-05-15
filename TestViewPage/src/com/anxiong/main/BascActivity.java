package com.anxiong.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;

import com.example.testviewpage.R;

public class BascActivity extends FragmentActivity {
	
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
	public void replace(Fragment fragment) {
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.framelayout, fragment).commit();
	}

	public void setVisibility(View v){
		switch (v.getVisibility()) {
		case View.GONE:
			v.setVisibility(View.VISIBLE);
		case View.VISIBLE:
			v.setVisibility(View.GONE);
			break;

		default:
			break;
		}
		
	}
}
