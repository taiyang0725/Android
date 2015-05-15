package com.anxiong.main;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anxiong.data.Tools;
import com.anxiong.fragment.BFragment.OnFromPicLocalListener;
import com.anxiong.fragment.MainFragment;
import com.anxiong.fragment.NetPicFragment;
import com.anxiong.fragment.NetPicFragment.OnFromMain0Listener;
import com.anxiong.fragment.PicLocalFragment;
import com.anxiong.fragment.PicLocalFragment.OnFromMainListener;
import com.anxiong.view.MainPopupWindow;
import com.example.testviewpage.R;

public class MainActivity extends BascActivity implements
		OnFromPicLocalListener, OnFromMainListener, OnFromMain0Listener {

	private TextView txtTit;

	private ImageView imgBack;
	private ImageView imgPop;

	private LinearLayout[] layout;
	private ImageView[] img;
	private TextView[] txt;
	
	private  MainPopupWindow window;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();

	}

	private void initView() {

		layout = new LinearLayout[5];
		img = new ImageView[4];
		txt = new TextView[4];

		txtTit = (TextView) findViewById(R.id.txt);
		imgBack=  (ImageView) findViewById(R.id.img_back);
		imgPop=  (ImageView) findViewById(R.id.img_pop);
		imgPop.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 window=new MainPopupWindow(MainActivity.this,new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						Tools.showLog("Hello World!  "+"position:"+position+",id:"+id);
						window.dismiss();
						
					}
				});
				window.showPopupWindow(imgPop);
				
			}
		});

		img[0] = (ImageView) findViewById(R.id.btn_a);
		img[1] = (ImageView) findViewById(R.id.btn_b);
		img[2] = (ImageView) findViewById(R.id.btn_c);
		img[3] = (ImageView) findViewById(R.id.btn_d);

		txt[0] = (TextView) findViewById(R.id.txt_a);
		txt[1] = (TextView) findViewById(R.id.txt_b);
		txt[2] = (TextView) findViewById(R.id.txt_c);
		txt[3] = (TextView) findViewById(R.id.txt_d);

		layout[0] = (LinearLayout) findViewById(R.id.a);
		layout[1] = (LinearLayout) findViewById(R.id.b);
		layout[2] = (LinearLayout) findViewById(R.id.c);
		layout[3] = (LinearLayout) findViewById(R.id.d);
		layout[4] = (LinearLayout) findViewById(R.id.include);

		replace(new MainFragment(layout, img, txt, txtTit, 0));

	}

	@Override
	public void fromPicLocal() {
		replace(new PicLocalFragment(txtTit, imgBack,layout));

	}

	@Override
	public void onFromMain() {
		
		imgBack.setVisibility(View.GONE);
		layout[4].setVisibility(View.VISIBLE);
		txtTit.setText("АВаж");
		replace(new MainFragment(layout, img, txt, txtTit, 1));

	}

	@Override
	public void fromNet() {
		replace(new NetPicFragment(txtTit,imgBack,layout));

	}

	

}
