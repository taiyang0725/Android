package com.feicui.news.fragment;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.feicui.news.BaseFragment;
import com.feicui.news.R;
import com.feicui.news.adapter.CmtAdapter;
import com.feicui.news.data.CmtData;
import com.feicui.news.mode.CmtList;
import com.feicui.news.mode.NewsList;
import com.feicui.news.xlistview.XListView;

/** 显示评论的界面 */
public class CmtFragment extends BaseFragment {
	private View view;

	private XListView listView;

	private ArrayList<CmtList> list;
	/** 标题小房子按钮 */
	private ImageView imageView;
	/** 标题栏中的字 */
	private TextView txt;
	/** 转圈加载符 */
	private ProgressDialog progressDialog;

	private OnCmtListener callBack;

	private NewsList newsList;

	/** 评论界面回调接口 */
	public interface OnCmtListener {
		/** 返回主界面 */
		void onFromMenu();

	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				list = (ArrayList<CmtList>) msg.obj;
				CmtAdapter adapter = new CmtAdapter(getActivity(), list);
				listView.setAdapter(adapter);
				progressDialog.cancel();
				break;
			case 2:
				progressDialog.cancel();
				Toast.makeText(getActivity(), R.string.network_anomaly,
						Toast.LENGTH_SHORT).show();
				break;
			case 3:
				progressDialog.cancel();

				break;

			default:
				break;
			}

		}
	};

	public CmtFragment(ImageView imageView, TextView txt) {

		this.imageView = imageView;
		this.txt = txt;
	}

	public CmtFragment(NewsList newsList) {

		this.newsList = newsList;

	}

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);
		try {
			callBack = (OnCmtListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnHeadlineSelectedListener");
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		list = new ArrayList<CmtList>();

		CmtData.getCmtList(getActivity(), handler, newsList.getNid(), "3");

		progressDialog = ProgressDialog.show(getActivity(), null,
				getResources().getString(R.string.defy_death));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.cmtfargment, container, false);

		initView(view);
		return view;
	}

	private void initView(View view2) {

		imageView.setBackgroundResource(R.drawable.arrow_white_left);
		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				callBack.onFromMenu();

			}
		});

		txt.setText(R.string.comment_interface);
		listView = (XListView) view2.findViewById(R.id.xListView_cmt);
		CmtAdapter adapter = new CmtAdapter(getActivity(), list);
		listView.setAdapter(adapter);

	}

}
