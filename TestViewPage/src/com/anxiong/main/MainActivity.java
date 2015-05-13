package com.anxiong.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.anxiong.fragment.MainFragment;
import com.example.testviewpage.R;

public class MainActivity extends BascActivity {

	private TextView txt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();

	}

	private void initView() {
		txt = (TextView) findViewById(R.id.txt);
		replace(new MainFragment(txt));

	}

	
}
