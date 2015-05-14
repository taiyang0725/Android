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
 * 这是一个logo界面类
 * */
public class SplashActivity extends BaseActivity implements AnimationListener {
	/**
	 * 动画ImageView
	 * */
	private ImageView img;
	/**
	 * 版本号
	 * */
	private TextView txt;

	/**
	 * 动画
	 * */
	private Animation animation;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_splash);

		initAnim();

		initView();
	}

	/** 动画初始化 */
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

	/** 跳转其他界面的方法 */
	private void jump(Class<?> cls) {

		Intent intent = new Intent(this, cls);

		startActivity(intent);

	}

}
