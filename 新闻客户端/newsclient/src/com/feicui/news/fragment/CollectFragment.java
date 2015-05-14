package com.feicui.news.fragment;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.feicui.news.BaseFragment;
import com.feicui.news.R;
import com.feicui.news.adapter.CollectAadapter;
import com.feicui.news.db.NewsDbHelper;
import com.feicui.news.mode.NewsList;
import com.feicui.news.swipelistview.BaseSwipeListViewListener;
import com.feicui.news.swipelistview.SwipeListView;
import com.feicui.news.util.ImageLoader.OnImageLoadListener;
import com.feicui.news.util.LogUtil;
import com.feicui.news.util.NewsDbUtil;

/**
 * 显示收藏界面
 * */
public class CollectFragment extends BaseFragment implements
		OnImageLoadListener, OnClickListener {
	private View view;

	private ListView lst;
	/** 滑动删除控件 */
	private SwipeListView mSwipeListView;

	private CollectAadapter adapter;

	/** 新闻列表 */
	private ArrayList<NewsList> list;

	/** 小房子按钮 */
	private ImageView imageView;

	/** 标题中间的文字 */
	private TextView txt;

	public static int deviceWidth;

	private int index;

	private NewsDbUtil dbUtil;

	public CollectFragment(ImageView imageView, TextView txt) {
		super();
		this.imageView = imageView;
		this.txt = txt;
	}

	private OnCollectListener callBack;

	/** 图片界面通讯接口 */
	public interface OnCollectListener {
		/** 图片界面通讯方法 */
		void onFromMenu();

	}

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);

		try {
			callBack = (OnCollectListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnHeadlineSelectedListener");
		}

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		dbUtil = new NewsDbUtil(getActivity());
		list = dbUtil.queryNewsListLove();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.collect, null);

		initView();

		initBack();
		return view;
	}

	private void initBack() {
		imageView.setBackgroundResource(R.drawable.arrow_white_left);
		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				callBack.onFromMenu();
			}
		});

		txt.setText(R.string.favorite);
	}

	private void initView() {

		mSwipeListView = (SwipeListView) view.findViewById(R.id.swipelistview);

		adapter = new CollectAadapter(getActivity(), list);

		adapter.setOnClick(this);

		deviceWidth = getDeviceWidth();

		mSwipeListView.setAdapter(adapter);

		mSwipeListView
				.setSwipeListViewListener(new TestBaseSwipeListViewListener());

		reload();

	}

	private int getDeviceWidth() {
		return getResources().getDisplayMetrics().widthPixels;
	}

	private void reload() {
		mSwipeListView.setSwipeMode(SwipeListView.SWIPE_MODE_LEFT);
		mSwipeListView.setSwipeActionLeft(SwipeListView.SWIPE_ACTION_REVEAL);
		// mSwipeListView.setSwipeActionRight(settings.getSwipeActionRight());
		mSwipeListView.setOffsetLeft(deviceWidth * 3 / 4);
		// mSwipeListView.setOffsetRight(convertDpToPixel(settings.getSwipeOffsetRight()));
		mSwipeListView.setAnimationTime(0);
		mSwipeListView.setSwipeOpenOnLongPress(false);
	}

	class TestBaseSwipeListViewListener extends BaseSwipeListViewListener {

		@Override
		public void onClickFrontView(int position) {
			super.onClickFrontView(position);

			index = position;
			Toast.makeText(getActivity(), "1", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onDismiss(int[] reverseSortedPositions) {
			for (int position : reverseSortedPositions) {
				LogUtil.d("adasdas" + position);

				list.remove(position);

			}
			adapter.notifyDataSetChanged();
		}
	}

	@Override
	public void onImage(Bitmap bitmap, String path) {
		ImageView view = (ImageView) lst.findViewWithTag(path);
		if (bitmap != null) {
			view.setImageBitmap(bitmap);
		}
	}

	@Override
	public void onClick(View v) {
		mSwipeListView.closeAnimate(index);
		mSwipeListView.dismiss(index);
		LogUtil.d("adasdas" + index);
		dbUtil.delete(NewsDbHelper.TABLE_NEWS_LOVE, list.get(index).getNid());

	}
}
