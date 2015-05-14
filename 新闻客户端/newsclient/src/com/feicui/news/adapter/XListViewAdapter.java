package com.feicui.news.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.feicui.news.R;
import com.feicui.news.mode.NewsList;
import com.feicui.news.util.ImageLoader;
import com.feicui.news.util.ImageLoader.OnImageLoadListener;

/**
 * 这是一个上拉刷新listview的适配器
 * */
public class XListViewAdapter extends BaseAdapter {

	private Context context;

	private AnimationDrawable animationDrawable;

	/** 新闻列表 */
	private ArrayList<NewsList> list;

	private LayoutInflater layoutInflater;

	private OnImageLoadListener onImageLoad;

	public void setOnImageLoad(OnImageLoadListener onImageLoad) {

		this.onImageLoad = onImageLoad;

	}

	public XListViewAdapter(Context context, ArrayList<NewsList> list) {

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
		if (position == 1) {
			// convertView = layoutInflater.inflate(R.layout.qrcode, null);

			// animationDrawable = (AnimationDrawable) context.getResources()
			// .getDrawable(R.anim.rabbit);
			// imgView.setBackgroundDrawable(animationDrawable);
			// imgView.setBackgroundResource(R.drawable.dice_6);
			// imgView = (ImageView) convertView;
		}

		ViewHolder holder;
		if (convertView == null) {

			holder = new ViewHolder();

			convertView = layoutInflater.inflate(R.layout.xlistviewitem, null);

			holder.imgIcon = (ImageView) convertView
					.findViewById(R.id.img_news_icon);

			holder.txtTilel = (TextView) convertView
					.findViewById(R.id.txt_news_title);

			holder.txt = (TextView) convertView
					.findViewById(R.id.txt_news_summary);

			holder.txtTimes = (TextView) convertView
					.findViewById(R.id.txt_news_time);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		ImageLoader loader = new ImageLoader(context);

		loader.setOnImageLoadListener(onImageLoad);
		Bitmap bitmap = loader.getImage(list.get(position).getIcon());
		// Bitmap bitmap = loader
		// .getImage("http://img0.bdstatic.com/static/common/widget/search_box_search/logo/logo_3b6de4c.png");
		if (bitmap != null) {
			holder.imgIcon.setImageBitmap(bitmap);
		}
		holder.imgIcon.setTag(list.get(position).getIcon());

		holder.txtTilel.setText(list.get(position).getTitle());
		holder.txt.setText(list.get(position).getSummary());
		holder.txtTimes.setText(list.get(position).getStamp());

		return convertView;

	}

	/**
	 * 这是一个管理view的类
	 * */
	class ViewHolder {
		/**
		 * 新闻图标
		 * */
		private ImageView imgIcon;
		/**
		 * 新闻标题
		 * */
		private TextView txtTilel;
		/**
		 * 新闻摘要
		 * */
		private TextView txt;
		/**
		 * 新闻时间
		 * */
		private TextView txtTimes;

		private ViewPager viewPager;

	}

}
