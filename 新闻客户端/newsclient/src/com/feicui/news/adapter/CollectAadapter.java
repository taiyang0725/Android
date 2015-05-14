package com.feicui.news.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.feicui.news.R;
import com.feicui.news.mode.NewsList;
import com.feicui.news.util.ImageLoader;
import com.feicui.news.util.ImageLoader.OnImageLoadListener;

public class CollectAadapter extends BaseAdapter {

	/** �����б� */
	private ArrayList<NewsList> list;

	private Context context;

	private LayoutInflater layoutInflater;

	/** ����¼� */
	private OnClickListener onClick;

	/** ����¼��ص����� */
	public void setOnClick(OnClickListener onClick) {
		this.onClick = onClick;
	}

	public CollectAadapter(Context context, ArrayList<NewsList> list) {
		super();
		this.list = list;

		this.context = context;

		this.layoutInflater = LayoutInflater.from(context);

	}

	private OnImageLoadListener onImageLoad;

	public void setOnImageLoad(OnImageLoadListener onImageLoad) {

		this.onImageLoad = onImageLoad;

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
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		if (convertView == null) {

			holder = new ViewHolder();

			convertView = layoutInflater.inflate(R.layout.collectitem, null);

			holder.imgIcon = (ImageView) convertView
					.findViewById(R.id.img_news_icon);

			holder.txtTilel = (TextView) convertView
					.findViewById(R.id.txt_news_title);

			holder.txt = (TextView) convertView
					.findViewById(R.id.txt_news_summary);

			holder.txtTimes = (TextView) convertView
					.findViewById(R.id.txt_news_time);

			holder.button = (ImageButton) convertView
					.findViewById(R.id.imgbtn_swipe_delete);
			// holder.layout = (LinearLayout) convertView
			// .findViewById(R.id.layout_swipe_delete);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		ImageLoader loader = new ImageLoader(context);

		loader.setOnImageLoadListener(onImageLoad);
		Bitmap bitmap = loader.getImage(list.get(position).getIcon());

		if (bitmap != null) {
			holder.imgIcon.setImageBitmap(bitmap);
		}
		holder.imgIcon.setTag(list.get(position).getIcon());

		holder.txtTilel.setText(list.get(position).getTitle());
		holder.txt.setText(list.get(position).getSummary());
		holder.txtTimes.setText(list.get(position).getStamp());

		holder.button.setOnClickListener(onClick);
		// holder.layout.setOnClickListener(onClick);

		return convertView;

	}

	/**
	 * ����һ������view����
	 * */
	class ViewHolder {
		/**
		 * ����ͼ��
		 * */
		private ImageView imgIcon;
		/**
		 * ���ű���
		 * */
		private TextView txtTilel;
		/**
		 * ����ժҪ
		 * */
		private TextView txt;
		/**
		 * ����ʱ��
		 * */
		private TextView txtTimes;
		/** ɾ����ť */
		private LinearLayout layout;
		/** ɾ����ť */
		private ImageButton button;

	}

}
