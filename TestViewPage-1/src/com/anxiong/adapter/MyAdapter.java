package com.anxiong.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.anxiong.viewpageIndicator.IconPagerAdapter;

public class MyAdapter extends FragmentPagerAdapter implements IconPagerAdapter{

	private List<Fragment> list;
	 private int [] icon;
	

	public MyAdapter(FragmentManager fm) {
		super(fm);

	}

	public MyAdapter(FragmentManager fm, List<Fragment> list, int [] icon) {
		super(fm);
		this.list = list;
		this.icon=icon;
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.isEmpty() ? 0 : list.size();
	}

	@Override
	public int getIconResId(int index) {
		// TODO Auto-generated method stub
		return icon[index % icon.length];
	}
	
	public void setCount(int count) {
        if (count > 0 && count <= 10) {
            int mcount=list.size();
            mcount=count;
            notifyDataSetChanged();
        }
    }

}
