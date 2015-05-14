package com.feicui.news.fragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.feicui.news.BaseFragment;
import com.feicui.news.R;
import com.feicui.news.mode.UserHome;
import com.feicui.news.slidingmenu.SlidingMenu;
import com.feicui.news.util.ShareUtil;

/**
 * ������ʾ�����˵�
 * */
public class SlidingMenuFragment extends BaseFragment implements
		OnClickListener {

	private SlidingMenu menu;
	/**
	 * �����˵����沼�ֿؼ�
	 * */
	private LinearLayout[] layouts;
	/**
	 * ��¼��ʾ
	 * */
	private TextView txt;
	/**
	 * �����˵�����
	 * */
	private View view;
	/** �û�ͼ�� */
	private ImageView imgIcon;
	/** �û���Ϣ */
	private TextView txtInfo;

	private Bitmap bitmap;
	private Bitmap bitmap1;

	/** ��������ʵ���� */
	private UserHome userHome;

	private OnSlidingMenuListener callBack;

	/** �����˵�����ص������ӿ� */
	public interface OnSlidingMenuListener {
		/** ������ͨѶ */
		void onFromMenu();

		/** ��½����ͨѶ */
		void onSlidingFromLogIn();

		/** ͼƬ����ͨѶ */
		void onFromPicture();

		/** �������� */
		void onFromMy();

		/** ���۽��� */
		void onFromCmt();

		/** �ղؽ��� */
		void onFromCollect();
	}

	public SlidingMenuFragment(SlidingMenu menu) {

		super();
		this.menu = menu;
	}

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);

		try {
			callBack = (OnSlidingMenuListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnHeadlineSelectedListener");
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.slidingmenu_left, container, false);

		initView(view);

		return view;
	}

	@Override
	public void onStart() {

		super.onStart();
		txt.setText(ShareUtil.getShare(getActivity(), "uid_name"));
	}

	private void initView(View view1) {

		txt = (TextView) view1.findViewById(R.id.txt_log_in);

		txt.setOnClickListener(this);

		layouts = new LinearLayout[6];
		/**
		 * �ҵ�����
		 * */
		layouts[0] = (LinearLayout) view1.findViewById(R.id.layout_my_news);
		/**
		 * ����
		 * */
		layouts[1] = (LinearLayout) view1.findViewById(R.id.layout_news);
		/**
		 * �ղ�
		 * */
		layouts[2] = (LinearLayout) view1.findViewById(R.id.layout_favorite);
		/**
		 * ����
		 * */
		layouts[3] = (LinearLayout) view1.findViewById(R.id.layout_local);
		/**
		 * ����
		 * */
		layouts[4] = (LinearLayout) view1.findViewById(R.id.layout_comment);
		/**
		 * ͼƬ
		 * */
		layouts[5] = (LinearLayout) view1.findViewById(R.id.layout_photo);

		for (int i = 0; i < layouts.length; i++) {
			layouts[i].setOnClickListener(this);
		}
		imgIcon = (ImageView) view1.findViewById(R.id.img_icon);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.txt_log_in:
			callBack.onSlidingFromLogIn();
			menu.showContent();

			break;
		case R.id.layout_my_news:
			// if (ShareUtil.getShare(getActivity(), "Token") == null) {
			//
			// } else {
			callBack.onFromMy();

			menu.showContent();
			break;
		case R.id.layout_news:
			callBack.onFromMenu();
			menu.showContent();
			break;
		case R.id.layout_favorite:
			callBack.onFromCollect();
			menu.showContent();

			Toast.makeText(getActivity(), "�ղ�", Toast.LENGTH_SHORT).show();
			break;
		case R.id.layout_local:
			Toast.makeText(getActivity(), "����", Toast.LENGTH_SHORT).show();
			break;
		case R.id.layout_comment:
			callBack.onFromCmt();
			menu.showContent();
			break;
		case R.id.layout_photo:
			callBack.onFromPicture();
			menu.showContent();

			break;

		default:
			break;
		}

	}
}
