package com.feicui.news.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.feicui.news.R;
import com.feicui.news.mode.NewsImage;
import com.feicui.news.util.ImageLoader;
import com.feicui.news.util.ImageLoader.OnImageLoadListener;
import com.feicui.news.util.LogUtil;

/**
 * 显示图片的适配器
 * */
public class PictureAdapter extends BaseAdapter {
	private Context context;

	/** 新闻列表 */

	private ArrayList<NewsImage> list;

	private LayoutInflater layoutInflater;

	private OnImageLoadListener onImageLoad;

	public void setOnImageLoad(OnImageLoadListener onImageLoad) {

		this.onImageLoad = onImageLoad;

	}

	public PictureAdapter(Context context, ArrayList<NewsImage> list) {
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
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {

			holder = new ViewHolder();

			convertView = layoutInflater.inflate(R.layout.picturefragmentitem,
					null);

			holder.img = (ImageView) convertView
					.findViewById(R.id.img_pic_fragment);

			holder.txt = (TextView) convertView.findViewById(R.id.txt_picture);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		ImageLoader loader = new ImageLoader(context);

		loader.setOnImageLoadListener(onImageLoad);

		Bitmap bitmap = loader.getImage(list.get(position).getImage());
		if (bitmap != null) {
			holder.img.setImageBitmap(bitmap);
		}

		holder.txt.setText(list.get(position).getIntroduct());

		holder.img.setTag(list.get(position).getImage());

		LogUtil.d("buxinga " + list.get(position).getIntroduct()
				+ list.get(position).getImage());

		return convertView;
	}

	/**
	 * 这是一个管理view的类
	 * */
	class ViewHolder {
		/**
		 * 图片
		 * */
		private ImageView img;
		/**
		 * 文字
		 * */
		private TextView txt;
	}

}
