package com.feicui.news.fragment;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.feicui.news.BaseFragment;
import com.feicui.news.R;
import com.feicui.news.adapter.XListViewAdapter;
import com.feicui.news.data.NewsData;
import com.feicui.news.mode.NewsList;
import com.feicui.news.util.ImageLoader.OnImageLoadListener;
import com.feicui.news.util.LogUtil;
import com.feicui.news.util.NewsDbUtil;
import com.feicui.news.util.SystemUtil;
import com.feicui.news.view.UserIconPopupWindow;
import com.feicui.news.xlistview.XListView;

/**
 * 这是一个显示新闻列表的Fragment类
 * */
public class XLstFragment extends BaseFragment implements OnItemClickListener,
		OnImageLoadListener {

	private OnMenuFragmentListener callBack;

	/**
	 * 上拉刷新listview
	 * */
	private XListView xListView;
	/**
	 * 返回布局view
	 * */
	private View view;

	/** 新闻列表 */
	private ArrayList<NewsList> list;
	/**
	 * 上拉刷新listview的适配器
	 * */
	private XListViewAdapter xListViewAdapter;

	private AlertDialog dialog;

	private UserIconPopupWindow window;

	private AnimationDrawable animationDrawable;

	private ImageView imgLoad1;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				list = (ArrayList<NewsList>) msg.obj;

				initVew();
				dialog.cancel();
				break;
			case 2:
				initData();
				initVew();
				dialog.cancel();

				LogUtil.d("失败吗");
				Toast.makeText(getActivity(), R.string.network_anomaly,
						Toast.LENGTH_SHORT).show();
				break;

			default:
				break;
			}
		};
	};

	/**
	 * 主界面回调接口
	 * */
	public interface OnMenuFragmentListener {

		/** 大图新闻 */
		void onMenuFromNews(NewsList list);

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			callBack = (OnMenuFragmentListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnHeadlineSelectedListener");
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		NewsData.getNewsLists(getActivity(), "1", "1", handler);

		list = new ArrayList<NewsList>();
		loadAnimation();

		// initData();
	}

	/** 加载中动画的方法 */
	private void loadAnimation() {

		animationDrawable = (AnimationDrawable) getActivity().getResources()
				.getDrawable(R.anim.load);

		View view1 = getActivity().getLayoutInflater().inflate(R.layout.load,
				null);
		imgLoad1 = (ImageView) view1.findViewById(R.id.img_load1);
		imgLoad1.setBackgroundDrawable(animationDrawable);

		animationDrawable.start();

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setView(view1);
		dialog = builder.create();
		dialog.show();

	}

	private void initData() {

		// if (!(NetworkUtil.isConnected(getActivity()))) {

		NewsDbUtil dbUtil = new NewsDbUtil(getActivity());
		list = dbUtil.queryNewsList();
		// dialog.cancel();

		// }

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.xlistview, container, false);

		initVew();

		return view;
	}

	private void initVew() {
		xListView = (XListView) view.findViewById(R.id.xListView);
		xListView.setPullLoadEnable(true);
		xListViewAdapter = new XListViewAdapter(getActivity(), list);
		xListView.setAdapter(xListViewAdapter);
		xListView.setOnItemClickListener(this);

	}

	private void onLoad() {
		xListView.stopRefresh();
		xListView.stopLoadMore();
		xListView.setRefreshTime(SystemUtil.getTime());
	}

	public void onRefresh() {
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {

				list.clear();

				xListViewAdapter = new XListViewAdapter(getActivity(), list);
				xListView.setAdapter(xListViewAdapter);
				onLoad();
			}

		}, 2000);
	}

	public void onLoadMore() {
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				NewsDbUtil dbUtil = new NewsDbUtil(getActivity());

				list = dbUtil.queryNewsList();
				xListViewAdapter.notifyDataSetChanged();
				onLoad();
			}
		}, 2000);
	}

	@Override
	public void onImage(Bitmap bitmap, String path) {

		ImageView view = (ImageView) xListView.findViewWithTag(path);
		if (bitmap != null) {
			view.setImageBitmap(bitmap);
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		callBack.onMenuFromNews(list.get(position - 1));

	}

}
