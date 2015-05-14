package com.feicui.news.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.feicui.news.R;

/** 用户图标PopupWindow */
public class UserIconPopupWindow extends PopupWindow {
	/** this中的布局文件 */
	private View conentView;

	/** 本地获取 */
	private TextView txtLocal;
	/** 在线拍照 */
	private TextView txtCam;

	public UserIconPopupWindow(final Activity context, OnClickListener onClick) {
		super(context);

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		conentView = inflater.inflate(R.layout.usericon, null);

		txtLocal = (TextView) conentView.findViewById(R.id.txt_get_local);
		txtLocal.setOnClickListener(onClick);

		txtCam = (TextView) conentView.findViewById(R.id.txt_online_photo);
		txtCam.setOnClickListener(onClick);

		int h = context.getWindowManager().getDefaultDisplay().getHeight();
		int w = context.getWindowManager().getDefaultDisplay().getWidth();

		// 设置PopupWindow的View
		this.setContentView(conentView);
		// 设置PopupWindow弹出窗体的宽
		this.setWidth(LayoutParams.WRAP_CONTENT);
		// 设置PopupWindow弹出窗体的高
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);

		this.setOutsideTouchable(true);

		// 刷新状态
		this.update();
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(R.color.green);
		// 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
		this.setBackgroundDrawable(dw);
		this.setAnimationStyle(android.R.style.Animation_Dialog);

	}

	public void showPopupWindow(View parent) {
		if (!this.isShowing()) {
			this.showAsDropDown(parent, parent.getLayoutParams().width / 3, 15);
		} else {
			this.dismiss();
		}

	}
}
