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

/** �û�ͼ��PopupWindow */
public class UserIconPopupWindow extends PopupWindow {
	/** this�еĲ����ļ� */
	private View conentView;

	/** ���ػ�ȡ */
	private TextView txtLocal;
	/** �������� */
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

		// ����PopupWindow��View
		this.setContentView(conentView);
		// ����PopupWindow��������Ŀ�
		this.setWidth(LayoutParams.WRAP_CONTENT);
		// ����PopupWindow��������ĸ�
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// ����SelectPicPopupWindow��������ɵ��
		this.setFocusable(true);

		this.setOutsideTouchable(true);

		// ˢ��״̬
		this.update();
		// ʵ����һ��ColorDrawable��ɫΪ��͸��
		ColorDrawable dw = new ColorDrawable(R.color.green);
		// ��back���������ط�ʹ����ʧ,������������ܴ���OnDismisslistener �����������ؼ��仯�Ȳ���
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
