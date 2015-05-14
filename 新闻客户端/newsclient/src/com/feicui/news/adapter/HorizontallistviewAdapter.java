package com.feicui.news.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.feicui.news.R;
import com.feicui.news.mode.SubGrp;
import com.feicui.news.util.LogUtil;

/**
 * 这是横向listview的适配器
 * */
public class HorizontallistviewAdapter extends BaseAdapter {

	private Context context;

	private ArrayList<SubGrp> title;

	private LayoutInflater layoutInflater;

	private int selectPosition;

	public HorizontallistviewAdapter(Context context, ArrayList<SubGrp> title) {
		super();

		this.context = context;

		this.title = title;

		this.layoutInflater = LayoutInflater.from(context);
	}

	public void setSelectPosition(int selectPosition) {
		this.selectPosition = selectPosition;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {

		return title.size();
	}

	@Override
	public Object getItem(int position) {

		return title.get(position);
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

			convertView = layoutInflater.inflate(
					R.layout.horizontallistviewitem, null);

			holder.txt = (TextView) convertView
					.findViewById(R.id.txt_hlis_item);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.txt.setText(title.get(position).getSubgroup());
		if (selectPosition == position) {
			LogUtil.d("selectPosition" + selectPosition);
			holder.txt.setTextColor(context.getResources()
					.getColor(R.color.red));
		} else {
			holder.txt.setTextColor(context.getResources().getColor(
					R.color.black));
		}

		return convertView;
	}

	/**
	 * 这是一个管理view的类
	 * */
	class ViewHolder {

		private TextView txt;
	}

}
