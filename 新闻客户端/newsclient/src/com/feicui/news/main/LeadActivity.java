package com.feicui.news.main;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.feicui.news.BaseActivity;
import com.feicui.news.R;
import com.feicui.news.adapter.LeadAdapter;
import com.feicui.news.util.LogUtil;
import com.feicui.news.util.ShareUtil;

/** 这是一个引导界面的类 */
public class LeadActivity extends BaseActivity implements OnPageChangeListener,
		OnClickListener {

	private ViewPager viewPager;

	/** 引导界面图片集合 */
	private ArrayList<String> list;

	/** View */
	private View view;

	/** 引导界面点点数组 */
	private ImageView[] img;
	/** 立即体验 */
	private ImageButton imgBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_lead);

		initData();

		initView();

	}

	private void initView() {

		viewPager = (ViewPager) findViewById(R.id.ViewPager);

		LeadAdapter leadAdapter = new LeadAdapter(this, list);

		leadAdapter.setOnClick(this);

		viewPager.setAdapter(leadAdapter);

		viewPager.setOnPageChangeListener(this);

		img = new ImageView[4];

		img[0] = (ImageView) findViewById(R.id.img_dot1);// 点1
		img[1] = (ImageView) findViewById(R.id.img_dot2);// 点2
		img[2] = (ImageView) findViewById(R.id.img_dot3);// 点3
		img[3] = (ImageView) findViewById(R.id.img_dot4);// 点4

	}

	private void initData() {

		list = new ArrayList<String>();
		list.add(R.drawable.guide1 + "");
		list.add(R.drawable.guide2 + "");
		list.add(R.drawable.guide3 + "");
		list.add(R.drawable.guide4 + "");

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int arg0) {
		LogUtil.d(arg0 + "qqqq");
		for (int i = 0; i < list.size(); i++) {
			if (arg0 == i) {
				img[arg0]
						.setBackgroundResource(R.drawable.indicator_dot_selected);
			} else if (arg0 != i) {
				img[i].setBackgroundResource(R.drawable.indicator_dot_normal);
			}

		}

	}

	@Override
	public void onClick(View v) {
		finish();
		jump(MainActivity.class);

		ShareUtil.setOnclick(this, true, "A");

	}

	/** 跳转其他界面的方法 */
	private void jump(Class<?> cls) {

		Intent intent = new Intent(this, cls);

		startActivity(intent);

	}

}
