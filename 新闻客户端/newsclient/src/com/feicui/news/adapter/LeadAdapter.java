package com.feicui.news.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.feicui.news.R;

/**
 * ����һ�����������������
 * */
public class LeadAdapter extends PagerAdapter {

	private Context context;

	/** �������漯�� */
	private ArrayList<String> list;
	/** ���ֽ����� */
	private LayoutInflater layoutInflater;

	/** ����¼� */
	private OnClickListener onClick;

	/** ����¼��ص����� */
	public void setOnClick(OnClickListener onClick) {
		this.onClick = onClick;
	}

	public LeadAdapter(Context context, ArrayList<String> list) {

		this.context = context;

		this.list = list;

		this.layoutInflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {

		return list.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {

		return arg0.equals(arg1);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {

		container.removeView((View) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {

		View view = layoutInflater.inflate(R.layout.leaditem, null);

		ImageView imageView = (ImageView) view.findViewById(R.id.img_guide1);
		imageView.setBackgroundResource(Integer.parseInt(list.get(position)));

		ImageButton imageButton = (ImageButton) view.findViewById(R.id.img_btn);
		if (position == list.size() - 1) {
			imageButton.setVisibility(View.VISIBLE);
		}
		/** �˴��������¼��������洦���¼� */
		imageButton.setOnClickListener(onClick);

		container.addView(view);
		return view;
	}
}
