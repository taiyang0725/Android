package com.feicui.news.main;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.feicui.news.BaseActivity;
import com.feicui.news.R;

/**
 * 这是显示二维码的界面
 * */
public class QrCodeAactivity extends BaseActivity implements OnClickListener {
	private AnimationDrawable animationDrawable;
	private AnimationDrawable animationDrawable1;
	private AnimationDrawable animationDrawable2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.qrcode);

		animationDrawable = (AnimationDrawable) getResources().getDrawable(
				R.anim.rabbit1);
		animationDrawable1 = (AnimationDrawable) getResources().getDrawable(
				R.anim.rabbit);
		animationDrawable2 = (AnimationDrawable) getResources().getDrawable(
				R.anim.user);

		RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout_qr_code);
		layout.setOnClickListener(this);

		ImageView img1 = (ImageView) findViewById(R.id.img_qr_code_anim);
		img1.setBackgroundDrawable(animationDrawable);
		animationDrawable.start();

		ImageView img2 = (ImageView) findViewById(R.id.img_qr_code_anim2);
		img1.setBackgroundDrawable(animationDrawable1);
		animationDrawable1.start();

		ImageView img3 = (ImageView) findViewById(R.id.img_qr_code_anim1);
		img3.setBackgroundDrawable(animationDrawable2);
		animationDrawable2.start();

	}

	@Override
	public void onClick(View v) {
		finish();

	}

}
