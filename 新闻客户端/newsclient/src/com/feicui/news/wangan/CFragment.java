package com.feicui.news.wangan;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.feicui.news.BaseFragment;
import com.feicui.news.R;

public class CFragment extends BaseFragment implements OnClickListener {
	private View view;

	private ImageView img;

	private AnimationDrawable animationDrawable;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		animationDrawable = (AnimationDrawable) getActivity().getResources()
				.getDrawable(R.anim.rabbit1);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.abcdefg, null);
		initView();
		return view;
	}

	private void initView() {

		img = (ImageView) view.findViewById(R.id.img_abcdefg);
		img.setBackgroundResource(R.drawable.c);
		img.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		img.setBackgroundDrawable(animationDrawable);
		animationDrawable.start();
	}
}
