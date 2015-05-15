package com.anxiong.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anxiong.adapter.PicAdapter;
import com.anxiong.data.Pic;
import com.example.testviewpage.R;

@SuppressLint("ValidFragment") 
public class PicLocalFragment extends BaseFragment implements OnClickListener {

	private View view;
	private ViewPager pager;
	private int[] lst;

	private TextView txtTit;
	private ImageView imgBack;
	
	private LinearLayout[] layout;
	
	private OnFromMainListener callBack;

	
	public PicLocalFragment(TextView txtTit, ImageView imgBack,LinearLayout[] layout) {
		super();
		this.txtTit = txtTit;
		this.imgBack=imgBack;
		
		this.layout=layout;
	}
	
	public interface OnFromMainListener{
		
		void onFromMain();
	}
	
	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		
		try {
			callBack =   (OnFromMainListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFromMainListener");
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		txtTit.setText("图片来自本地");
		
		imgBack.setVisibility(View.VISIBLE);
		layout[4].setVisibility(View.GONE);
		
		imgBack.setOnClickListener(this);

		lst = Pic.getPicFoc();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.pic, null);

		init();
		return view;
	}

	private void init() {

		pager = (ViewPager) view.findViewById(R.id.pic_page);
		PicAdapter pAdapter = new PicAdapter(lst, getActivity());
		pager.setAdapter(pAdapter);

	}

	@Override
	public void onClick(View v) {
		callBack.onFromMain();
		
		
	}

	

}
