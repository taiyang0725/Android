package com.example.wechat;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends Activity implements OnCheckedChangeListener {

	private RadioGroup rgp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initView();
    }

	private void initView() {
		
		rgp=(RadioGroup) findViewById(R.id.main_rgp);
		rgp.setOnCheckedChangeListener(this);
		
		
		
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.radiobutton1:
			Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
			break;
		case R.id.radiobutton2:
			Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
			break;
		case R.id.radiobutton3:
			Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
			break;
		case R.id.radiobutton4:
			Toast.makeText(this, "4", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
		
	}


   
}
