package com.anxiong.fragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anxiong.data.ReadTxt;
import com.anxiong.data.Tools;
import com.example.testviewpage.R;

public class BFragment extends Fragment {

	private View view;

	private String txt;

	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.b, null);

		return view;
	}

	private void data() {
		
	}

}
