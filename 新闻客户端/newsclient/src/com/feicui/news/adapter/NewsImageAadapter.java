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
import com.feicui.news.mode.NewsImage;

/**
 * 新闻内容适配器
 * */
public class NewsImageAadapter extends BaseAdapter {

	private Context context;

	private ArrayList<NewsImage> list;

	private LayoutInflater layoutInflater;

	public NewsImageAadapter(Context context, ArrayList<NewsImage> list) {

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

			convertView = layoutInflater.inflate(R.layout.newsimageitem, null);
			holder.txt = (TextView) convertView
					.findViewById(R.id.txt_news_image_image);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.txt.setText(list.get(position).getIntroduct());

		return convertView;
	}

	/**
	 * 这是一个管理view的类
	 * */
	class ViewHolder {
		/** 新闻内容 */
		private TextView txt;
		/** 新闻图片 */
		private ImageView img;
	}

}
