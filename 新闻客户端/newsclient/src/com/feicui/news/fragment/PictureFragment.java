package com.feicui.news.fragment;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.feicui.news.adapter.PictureAdapter;
import com.feicui.news.data.NewsData;
import com.feicui.news.mode.NewsImage;
import com.feicui.news.util.ImageLoader.OnImageLoadListener;

/**
 * ����һ����ʾͼƬ��fragment
 * */
public class PictureFragment extends BaseFragment implements
		OnImageLoadListener, OnClickListener {

	private View view;

	private String[] pic;

	private ArrayList<NewsImage> list;

	private ListView lst;

	private ImageView imageView;

	private TextView txt;

	private OnPictureListener callBack;

	/** ͼƬ����ͨѶ�ӿ� */
	public interface OnPictureListener {
		/** ͼƬ����ͨѶ���� */
		void onFromMenu();

	}

	public PictureFragment(ImageView imageView, TextView txt) {
		super();
		this.imageView = imageView;
		this.txt = txt;

	}

	/** תȦ���ط� */
	private ProgressDialog progressDialog;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:

				list = (ArrayList<NewsImage>) msg.obj;
				initView();
				progressDialog.cancel();
				break;
			case 2:
				progressDialog.cancel();
				Toast.makeText(getActivity(), R.string.network_anomaly,
						Toast.LENGTH_SHORT).show();

				break;

			default:
				break;
			}

		}
	};

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);

		try {
			callBack = (OnPictureListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnHeadlineSelectedListener");
		}

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		list = new ArrayList<NewsImage>();
		progressDialog = ProgressDialog.show(getActivity(), null,
				getResources().getString(R.string.defy_death));

		NewsData.getNewsImage(getActivity(), handler);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.picturefragment, container, false);
		initView();
		return view;
	}

	private void initView() {

		lst = (ListView) view.findViewById(R.id.lst_picture);
		PictureAdapter adapter = new PictureAdapter(getActivity(), list);
		lst.setAdapter(adapter);

		imageView.setBackgroundResource(R.drawable.arrow_white_left);
		imageView.setOnClickListener(this);

		txt.setText(R.string.picture);
	}

	@Override
	public void onImage(Bitmap bitmap, String path) {
		ImageView img = (ImageView) lst.findViewWithTag(path);
		if (bitmap != null) {

			img.setImageBitmap(bitmap);
		}

	}

	@Override
	public void onClick(View v) {
		callBack.onFromMenu();

	}

}
