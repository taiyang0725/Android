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
import com.feicui.news.mode.CmtList;

/** 评论界面适配器 */
public class CmtAdapter extends BaseAdapter {

	private Context context;

	private ArrayList<CmtList> list;

	private LayoutInflater inflater;

	public CmtAdapter(Context context, ArrayList<CmtList> list) {
		super();
		this.context = context;
		this.list = list;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHandler handler;
		if (convertView == null) {
			handler = new ViewHandler();

			convertView = inflater.inflate(R.layout.cmtitem, null);

			handler.imgIcon = (ImageView) convertView
					.findViewById(R.id.img_cmt_icon);
			handler.txtName = (TextView) convertView
					.findViewById(R.id.txt_cmt_name);
			handler.txtCon = (TextView) convertView
					.findViewById(R.id.txt_cmt_content);
			handler.txtTime = (TextView) convertView
					.findViewById(R.id.txt_cmt_time);
			convertView.setTag(handler);

		} else {
			handler = (ViewHandler) convertView.getTag();
		}
		handler.txtName.setText(list.get(position).getUid());
		handler.txtCon.setText(list.get(position).getContent());
		handler.txtTime.setText(list.get(position).getStamp());

		return convertView;
	}

	/**
	 * 管理view的类
	 * */
	class ViewHandler {
		/** 评论者图像 */
		private ImageView imgIcon;
		/** 姓名 */
		private TextView txtName;
		/** 内容 */
		private TextView txtCon;
		/** 时间 */
		private TextView txtTime;

	}

}
