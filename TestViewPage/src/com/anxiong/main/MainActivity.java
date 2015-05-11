package com.anxiong.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anxiong.fragment.AFragment;
import com.anxiong.fragment.BFragment;
import com.anxiong.fragment.CFragment;
import com.anxiong.fragment.DFragment;
import com.anxiong.fragment.MainFragment;
import com.example.testviewpage.R;

public class MainActivity extends FragmentActivity implements OnClickListener {
	
	private ImageView[] img;
	private TextView[] txt;

	private LinearLayout[] layout;

	
	{
		Integer[] bg = { R.drawable.alc, R.drawable.ala, R.drawable.ale,
				R.drawable.alg };
		Integer[] bg_s = { R.drawable.alb, R.drawable.al_, R.drawable.ald,
				R.drawable.alf };

	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		img = new ImageView[4];
		txt = new TextView[4];
		layout = new LinearLayout[4];

		initView();

		replace(new MainFragment());
	}

	private void initView() {

		findViewById(R.id.a).setOnClickListener(this);
		findViewById(R.id.b).setOnClickListener(this);
		findViewById(R.id.c).setOnClickListener(this);
		findViewById(R.id.d).setOnClickListener(this);

		img[0] = (ImageView) findViewById(R.id.btn_a);
		img[1] = (ImageView) findViewById(R.id.btn_b);
		img[2] = (ImageView) findViewById(R.id.btn_c);
		img[3] = (ImageView) findViewById(R.id.btn_d);

		txt[0] = (TextView) findViewById(R.id.txt_a);
		txt[1] = (TextView) findViewById(R.id.txt_b);
		txt[2] = (TextView) findViewById(R.id.txt_c);
		txt[3] = (TextView) findViewById(R.id.txt_d);

	}

	/**
	 * Fragment�滻�ķ���
	 * */
	public void replace(Fragment fragment) {
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.framelayout, fragment).commit();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.a:
			replace(new AFragment());
			break;
		case R.id.b:
			replace(new BFragment());
			break;
		case R.id.c:
			replace(new CFragment());
			break;
		case R.id.d:
			replace(new DFragment());
			break;

		default:
			break;
		}

	}

	
}
