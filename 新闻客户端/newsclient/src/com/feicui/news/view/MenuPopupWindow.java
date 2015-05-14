package com.feicui.news.view;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.feicui.news.R;
import com.feicui.news.adapter.PopupAdapter;
import com.feicui.news.data.PageBasicData;
import com.feicui.news.mode.UIEntity;

/**
 * ���������Ͻ�PopupWinder
 * */
public class MenuPopupWindow extends PopupWindow {

	private View conentView;
	private ListView lst;
	private ArrayList<UIEntity> list;

	// /** ����¼��ص����� */
	// public void setOnItemClick(OnItemClickListener OnItemClick) {
	// this.OnItemClick = OnItemClick;
	// }

	public MenuPopupWindow(final Activity context,
			OnItemClickListener OnItemClick) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		list = PageBasicData.getPopup(context);

		conentView = inflater.inflate(R.layout.popupwindow, null);
		lst = (ListView) conentView.findViewById(R.id.lst_popup);
		PopupAdapter adapter = new PopupAdapter(context, list);
		lst.setOnItemClickListener(OnItemClick);
		lst.setAdapter(adapter);

		int h = context.getWindowManager().getDefaultDisplay().getHeight();
		int w = context.getWindowManager().getDefaultDisplay().getWidth();
		// ����SelectPicPopupWindow��View

		this.setContentView(conentView);
		// ����SelectPicPopupWindow��������Ŀ�
		this.setWidth(w / 3);
		// ����SelectPicPopupWindow��������ĸ�
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
		// ����SelectPicPopupWindow�������嶯��Ч��
		// this.setAnimationStyle(R.style.);

	}

	public void showPopupWindow(View parent) {
		if (!this.isShowing()) {
			this.showAsDropDown(parent, parent.getLayoutParams().width / 4, 15);
		} else {
			this.dismiss();
		}

	}
}
