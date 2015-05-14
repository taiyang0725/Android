package com.feicui.news.fragment;

import org.apache.http.Header;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.feicui.news.BaseFragment;
import com.feicui.news.R;
import com.feicui.news.util.LogUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

/** 天气预报界面 */
public class WeatherFragment extends BaseFragment {

	private final String TAG = "天气预报界面";
	private View view;

	/** 天气图片 */
	private ImageView imageView_user_imgOne;
	/** 显示未来天数的数字 */
	public static int dayCount = 3;
	/** 城市名称 */
	private TextView textView_user_city;
	/** 右上角方向日期 */
	private TextView textView_user_date;
	/** 今天温度 */
	private TextView textView_user_temp;
	/** 滚动显示穿衣指数 */
	private TextView textView_user_index_d;
	/** 紫外线 */
	private TextView textView_user_index_uv;
	/** 洗车 */
	private TextView textView_user_index_xc;
	/** 旅游 */
	private TextView textView_user_index_tr;
	/** 舒适指数 */
	private TextView textView_user_index_co;
	/** 晨练 */
	private TextView textView_user_index_cl;
	/** 晾晒 */
	private TextView textView_user_index_ls;
	/** 过敏 */
	private TextView textView_user_index_ag;

	/** 天气 */
	private TextView textView_user_weather;
	/** 显示其它五天信息的分页 */
	private LinearLayout linear_user_first_other;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		requestUrlData();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.weather_forecast, null);

		return view;
	}

	/**
	 * 请求网络数据
	 */
	private void requestUrlData() {
		AsyncHttpClient client = new AsyncHttpClient();
		String url = "http://m.weather.com.cn/data/101110101.html";
		client.get(url, new TextHttpResponseHandler() {

			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {

				LogUtil.d(arg2);

			}

			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2,
					Throwable arg3) {

			}
		});

	}

}
