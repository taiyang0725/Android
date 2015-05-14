package com.feicui.news.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.feicui.news.BaseActivity;
import com.feicui.news.R;
import com.feicui.news.util.ShareUtil;
import com.feicui.news.util.SystemUtil;

/**
 * ����һ��logo������
 * */
public class SplashActivity extends BaseActivity implements AnimationListener {
	/**
	 * ����ImageView
	 * */
	private ImageView img;
	/**
	 * �汾��
	 * */
	private TextView txt;

	/**
	 * ����
	 * */
	private Animation animation;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_splash);

		initAnim();

		initView();
	}

	/** ������ʼ�� */
	private void initAnim() {
		animation = AnimationUtils.loadAnimation(this, R.anim.logo_anim);

	}

	private void initView() {

		txt = (TextView) findViewById(R.id.txt_version_num);
		txt.setText("v" + SystemUtil.getVersionName(this));

		img = (ImageView) findViewById(R.id.img_splash_loading_item);
		img.setAnimation(animation);
		animation.setAnimationListener(this);

	}

	@Override
	public void onAnimationStart(Animation animation) {

	}

	@Override
	public void onAnimationEnd(Animation animation) {
		if (ShareUtil.isOnclick(this, "A")) {

			finish();
			jump(MainActivity.class);

		} else {
			finish();
			jump(LeadActivity.class);
		}

	}

	@Override
	public void onAnimationRepeat(Animation animation) {

	}

	/** ��ת��������ķ��� */
	private void jump(Class<?> cls) {

		Intent intent = new Intent(this, cls);

		startActivity(intent);

	}

}
