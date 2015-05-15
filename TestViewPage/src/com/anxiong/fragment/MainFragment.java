package com.anxiong.fragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anxiong.adapter.MyAdapter;
import com.anxiong.data.Pic;
import com.example.testviewpage.R;

@SuppressLint("ValidFragment")
public class MainFragment extends BaseFragment implements OnPageChangeListener,
		OnClickListener {

	private View view;

	private ViewPager pager;
	private List<Fragment> list;
	private MyAdapter adapter;

	private LinearLayout[] layout;
	private ImageView[] img;
	private TextView[] txt;
	private int pageNum;

	private int[] picNor;
	private int[] picFoc;

	private TextView txtTit;// 标题
	

	

	

	public MainFragment(LinearLayout[] layout, ImageView[] img, TextView[] txt,
			TextView txtTit,int pageNum) {
		super();
		this.layout = layout;
		this.img = img;
		this.txt = txt;
		this.txtTit = txtTit;
		this.pageNum = pageNum;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		picNor = Pic.getPicNor();
		picFoc = Pic.getPicFoc();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.activity_base, null);
		init();

		return view;

	}

	private void init() {

		list = new ArrayList<Fragment>();
		list.add(new AFragment());
		list.add(new BFragment());
		list.add(new CFragment());
		list.add(new DFragment());

		pager = (ViewPager) view.findViewById(R.id.viewPage);
		pager.setOnPageChangeListener(this);

		adapter = new MyAdapter(getChildFragmentManager(), list);
		pager.setAdapter(adapter);

		for (int i = 0; i < layout.length; i++) {
			layout[i].setOnClickListener(this);
		}

		backPage(pageNum);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {}
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {}

	@Override
	public void onPageSelected(int arg0) {
		pager.setCurrentItem(arg0);

		switch (arg0) {
		case 0:
			setImgBg(img, 0);
			setTxtColor(txt, 0);

			break;
		case 1:
			setImgBg(img, 1);
			setTxtColor(txt, 1);

			break;
		case 2:
			setImgBg(img, 2);
			setTxtColor(txt, 2);

			break;
		case 3:
			setImgBg(img, 3);
			setTxtColor(txt, 3);
			break;

		default:
			break;
		}

	}

	@Override
	public void onDetach() {
		super.onDetach();
		// ��fragment����ʱͨ���������mChildFragmentManagerҲ����
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

	private void backPage(int index) {

		pager.setCurrentItem(index);

		switch (index) {
		case 0:
			setImgBg(img, 0);
			setTxtColor(txt, 0);

			break;
		case 1:
			setImgBg(img, 1);
			setTxtColor(txt, 1);

			break;
		case 2:
			setImgBg(img, 2);
			setTxtColor(txt, 2);

			break;
		case 3:
			setImgBg(img, 3);
			setTxtColor(txt, 3);
			break;

		default:
			break;
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.a:
			pager.setCurrentItem(0);
			setImgBg(img, 0);
			setTxtColor(txt, 0);
			break;
		case R.id.b:
			pager.setCurrentItem(1);
			setImgBg(img, 1);
			setTxtColor(txt, 1);
			break;
		case R.id.c:
			pager.setCurrentItem(2);
			setImgBg(img, 2);
			setTxtColor(txt, 2);
			break;
		case R.id.d:
			pager.setCurrentItem(3);
			setImgBg(img, 3);
			setTxtColor(txt, 3);
			break;

		default:
			break;
		}

	}

	public void setImgBg(ImageView[] img, int i) {
		img[i].setBackgroundResource(picFoc[i]);
		for (int j = 0; j < img.length; j++) {
			if (j != i) {
				img[j].setBackgroundResource(picNor[j]);
			}
		}

	}

	public void setTxtColor(TextView[] txt, int i) {
		txt[i].setTextColor(Color.parseColor("#45C01A"));
		

		for (int j = 0; j < txt.length; j++) {
			if (j != i) {
				txt[j].setTextColor(Color.parseColor("#444444"));
			}

		}

	}

}
