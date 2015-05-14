package com.feicui.news.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.feicui.news.R;

/** 动画加载PopupWinder */
public class LoadPopupWindow extends PopupWindow {
	private View conentView;

	private AnimationDrawable animationDrawable;

	private ImageView imgLoad1;

	private int h;

	public LoadPopupWindow(final Activity context) {
		super(context);

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		animationDrawable = (AnimationDrawable) context.getResources()
				.getDrawable(R.anim.load);

		conentView = inflater.inflate(R.layout.load, null);

		imgLoad1 = (ImageView) conentView.findViewById(R.id.img_load1);
		imgLoad1.setBackgroundDrawable(animationDrawable);

		animationDrawable.start();

		h = context.getWindowManager().getDefaultDisplay().getHeight();

		// 设置PopupWindow的View

		this.setContentView(conentView);
		// 设置PopupWindow弹出窗体的宽
		this.setWidth(LayoutParams.WRAP_CONTENT);
		// 设置PopupWindow弹出窗体的高
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// 刷新状态
		this.update();

	}

	public void showPopupWindow(View parent) {
		if (!this.isShowing()) {
			this.showAsDropDown(parent, 0, h / 2);
		} else {
			this.dismiss();
		}

	}

}
