package com.feicui.news.wangan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.feicui.news.BaseFragment;
import com.feicui.news.R;

public class FFragment extends BaseFragment {
	private View view;

	private ImageView img;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.abcdefg, null);
		initView();
		return view;
	}

	private void initView() {

		img = (ImageView) view.findViewById(R.id.img_abcdefg);
		img.setBackgroundResource(R.drawable.e);

	}
}
