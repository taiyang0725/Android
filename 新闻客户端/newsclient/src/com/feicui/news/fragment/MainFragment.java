package com.feicui.news.fragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.feicui.news.BaseFragment;
import com.feicui.news.R;
import com.feicui.news.adapter.HorizontallistviewAdapter;
import com.feicui.news.adapter.MyFragmentAdapter;
import com.feicui.news.data.NewsData;
import com.feicui.news.horizontallistview.HorizontalListView;
import com.feicui.news.mode.SubGrp;
import com.feicui.news.util.NewsDbUtil;
import com.feicui.news.wangan.AFragment;
import com.feicui.news.wangan.BFragment;
import com.feicui.news.wangan.CFragment;
import com.feicui.news.wangan.DFragment;
import com.feicui.news.wangan.EFragment;
import com.feicui.news.wangan.FFragment;
import com.feicui.news.wangan.GFragment;

/**
 * 这是一个主界面显示新闻标题的Fragment类
 * */
public class MainFragment extends BaseFragment implements OnItemClickListener,
		OnPageChangeListener {

	private View view;

	private ViewPager mViewPager;

	private List<Fragment> mFragmentList;

	private MyFragmentAdapter adapter;

	private AnimationDrawable animationDrawable;

	private AlertDialog dialog;

	private ImageView imgLoad1;

	/**
	 * 横向listview
	 * */
	private HorizontalListView horizontalListView;
	private HorizontallistviewAdapter horizontallistviewAdapter;
	/** 新闻分类 */

	private ArrayList<SubGrp> title;

	private Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:

				title = (ArrayList<SubGrp>) msg.obj;
				initView();
				dialog.cancel();

				break;
			case 2:
				initData();
				initView();
				dialog.cancel();
				Toast.makeText(getActivity(), R.string.network_anomaly,
						Toast.LENGTH_SHORT).show();
				break;

			default:
				break;
			}
		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		NewsData.getNewsTitle(getActivity(), handler);
		title = new ArrayList<SubGrp>();
		loadAnimation();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_main, null);
		initViewPager();

		return view;
	}

	private void initData() {

		// if (!(NetworkUtil.isConnected(getActivity()))) {

		NewsDbUtil dbUtil = new NewsDbUtil(getActivity());
		title = dbUtil.queryNewsSort();
		dialog.cancel();

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

	private void initView() {

		horizontalListView = (HorizontalListView) view
				.findViewById(R.id.horizontallistview);
		horizontallistviewAdapter = new HorizontallistviewAdapter(
				getActivity(), title);

		horizontalListView.setAdapter(horizontallistviewAdapter);
		horizontalListView.setOnItemClickListener(this);

	}

	private void initViewPager() {
		mFragmentList = new ArrayList<Fragment>();

		mFragmentList.add(new XLstFragment());
		mFragmentList.add(new AFragment());
		mFragmentList.add(new BFragment());
		mFragmentList.add(new CFragment());
		mFragmentList.add(new DFragment());
		mFragmentList.add(new EFragment());
		mFragmentList.add(new FFragment());
		mFragmentList.add(new GFragment());

		mViewPager = (ViewPager) view.findViewById(R.id.pager);
		mViewPager.setOnPageChangeListener(this);
		adapter = new MyFragmentAdapter(getChildFragmentManager(),
				mFragmentList);
		mViewPager.setAdapter(adapter);
	}

	/**
	 * HorizontalListView点击事件回调方法
	 * */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		mViewPager.setCurrentItem(position);
		horizontallistviewAdapter.setSelectPosition(position);

	}

	/**
	 * ViewPager滑动时间回调方法
	 * */
	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		mViewPager.setCurrentItem(arg0);
		horizontallistviewAdapter.setSelectPosition(arg0);

	}

	@Override
	public void onDetach() {
		super.onDetach();
		// 当fragment销毁时通过反射控制mChildFragmentManager也重置
		try {
			Field childFragmentManager = Fragment.class
					.getDeclaredField("mChildFragmentManager");
			childFragmentManager.setAccessible(true);
			childFragmentManager.set(this, null);

		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}

	}

}
