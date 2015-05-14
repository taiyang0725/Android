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

/** ����Ԥ������ */
public class WeatherFragment extends BaseFragment {

	private final String TAG = "����Ԥ������";
	private View view;

	/** ����ͼƬ */
	private ImageView imageView_user_imgOne;
	/** ��ʾδ������������ */
	public static int dayCount = 3;
	/** �������� */
	private TextView textView_user_city;
	/** ���ϽǷ������� */
	private TextView textView_user_date;
	/** �����¶� */
	private TextView textView_user_temp;
	/** ������ʾ����ָ�� */
	private TextView textView_user_index_d;
	/** ������ */
	private TextView textView_user_index_uv;
	/** ϴ�� */
	private TextView textView_user_index_xc;
	/** ���� */
	private TextView textView_user_index_tr;
	/** ����ָ�� */
	private TextView textView_user_index_co;
	/** ���� */
	private TextView textView_user_index_cl;
	/** ��ɹ */
	private TextView textView_user_index_ls;
	/** ���� */
	private TextView textView_user_index_ag;

	/** ���� */
	private TextView textView_user_weather;
	/** ��ʾ����������Ϣ�ķ�ҳ */
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
	 * ������������
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
