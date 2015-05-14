package com.feicui.news.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.feicui.news.R;
import com.feicui.news.mode.UIEntity;

/**
 * ����һ��PoppupWindow��������
 * */
public class PopupAdapter extends BaseAdapter {

	private Context context;

	private ArrayList<UIEntity> list;

	private LayoutInflater layoutInflater;

	public PopupAdapter(Context context, ArrayList<UIEntity> list) {
		super();
		this.context = context;
		this.list = list;
		this.layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {

		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;

		if (convertView == null) {

			holder = new ViewHolder();

			convertView = layoutInflater.inflate(R.layout.popupitem, null);

			holder.img = (ImageView) convertView
					.findViewById(R.id.img_popup_icon);

			holder.txt = (TextView) convertView
					.findViewById(R.id.txt_popup_name);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.img.setBackgroundResource(list.get(position).getIcon());

		holder.txt.setText(list.get(position).getName());

		return convertView;
	}

	/**
	 * ����һ������view����
	 * */
	class ViewHolder {
		/**
		 * Ӧ�ó���ͼ��
		 * */
		private ImageView img;
		/**
		 * Ӧ�ó�������
		 * */
		private TextView txt;

	}
}
