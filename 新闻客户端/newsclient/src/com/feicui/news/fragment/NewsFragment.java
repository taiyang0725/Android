package com.feicui.news.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.feicui.news.BaseFragment;
import com.feicui.news.R;
import com.feicui.news.data.CmtData;
import com.feicui.news.mode.NewsList;
import com.feicui.news.util.NewsDbUtil;
import com.feicui.news.util.ShareUtil;
import com.feicui.news.view.LoadPopupWindow;
import com.feicui.news.view.SharePopupWindow;

/**
 * ��ʾ��ͼ��������
 * */
public class NewsFragment extends BaseFragment implements OnClickListener,
		OnItemClickListener {
	/** �������ݽ��沼�� */
	private View view;
	/** �������� */
	private WebView webView;

	/** С���Ӱ�ť */
	private ImageView imageView;
	/** ���㰴ť */
	private ImageView imgThree;
	/** �����м������ */
	private TextView txt;

	private OnFromMenuListener callBack;
	/**
	 * ��������
	 * */
	private LoadPopupWindow window;
	/**
	 * һ������
	 * */
	private SharePopupWindow sharePopupWindow;
	/** ���� */
	private TextView txtShare;
	/** �ղ� */
	private TextView txtLove;
	/** ���� */
	private TextView txtCmt;
	/** ������д�� */
	private EditText edtCmt;

	private RelativeLayout layout;

	private AlertDialog dialog;

	/** ���Ž���ص��ӿ� */
	public interface OnFromMenuListener {
		/** ���Ž���ص����� */
		void onFromMenu();
	}

	private NewsList list;

	public NewsFragment(NewsList list, ImageView imageView, TextView txt,
			ImageView imgThree) {
		this.list = list;

		this.imageView = imageView;

		this.txt = txt;

		this.imgThree = imgThree;

	}

	/** תȦ���ط� */
	private ProgressDialog progressDialog;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:

				progressDialog.cancel();
				break;
			case 2:

				progressDialog.cancel();
				Toast.makeText(getActivity(), R.string.network_anomaly,
						Toast.LENGTH_SHORT).show();

				break;

			default:
				break;
			}

		}
	};

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);

		callBack = (OnFromMenuListener) activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		window = new LoadPopupWindow(getActivity());
		window.showPopupWindow(txt);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.news, container, false);
		initView(view);
		return view;
	}

	private void initView(View view0) {

		layout = (RelativeLayout) view.findViewById(R.id.layout_share);

		txtShare = (TextView) view.findViewById(R.id.txt_news_share);
		txtShare.setOnClickListener(this);

		txtLove = (TextView) view.findViewById(R.id.txt_news_favorite);
		txtLove.setOnClickListener(this);

		txtCmt = (TextView) view.findViewById(R.id.txt_news_comment);
		txtCmt.setOnClickListener(this);

		imageView.setBackgroundResource(R.drawable.arrow_white_left);
		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				callBack.onFromMenu();

			}
		});
		webView = (WebView) view0.findViewById(R.id.webview);

		webView.loadUrl(list.getLink());

		// WebSettings webSettings = webView.getSettings();
		//
		// // ����֧��JavaScript�ű�
		// webSettings.setJavaScriptEnabled(true);
		// // ���ÿ��Է����ļ�
		// webSettings.setAllowFileAccess(true);
		// // ����֧������
		// webSettings.setBuiltInZoomControls(true);

		webView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {

				super.onProgressChanged(view, newProgress);

				window.dismiss();
			}
		});
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				webView.loadUrl(url);
				return true;
			}

			/** ��ҳ��ʼ���ص�ʱ�� */
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {

				super.onPageStarted(view, url, favicon);

			}

			/** ��ҳ������ϵ�ʱ�� */
			@Override
			public void onPageFinished(WebView view, String url) {

				super.onPageFinished(view, url);
				viewGone(layout);
				window.dismiss();

			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.txt_news_comment:
			setCmt();
			break;
		case R.id.txt_news_favorite:
			NewsDbUtil dbUtil = new NewsDbUtil(getActivity());
			dbUtil.insertLove(list);

			Toast.makeText(getActivity(), R.string.favorite_succeed,
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.txt_news_share:
			setShare();
			break;

		default:
			break;
		}

	}

	/** ���� */
	private void setCmt() {

		View view1 = getActivity().getLayoutInflater().inflate(
				R.layout.shareedittext, null);
		edtCmt = (EditText) view1.findViewById(R.id.edt_shareedt);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setIcon(R.drawable.icon_file_text);
		builder.setTitle(R.string.comment_content);
		builder.setView(view1);
		builder.setNeutralButton(R.string.give_up, null);
		builder.setNegativeButton(R.string.comment_send,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

						progressDialog = ProgressDialog.show(getActivity(),
								null,
								getResources().getString(R.string.log_in_4));

						CmtData.getCmtCommit(getActivity(), handler,
								list.getNid(),
								ShareUtil.getShare(getActivity(), "Token"),
								edtCmt.getText().toString());

					}
				});
		dialog = builder.create();
		dialog.show();

	}

	/** һ������ */
	private void setShare() {
		// sharePopupWindow = new SharePopupWindow(getActivity(), this);
		// sharePopupWindow.showPopupWindow(imgThree);
		OnekeyShare share = new OnekeyShare();

		share.setTitle(getActivity().getResources()
				.getString(R.string.app_name));

		share.setText("Hello World");

		share.setSiteUrl(list.getLink());
		share.show(getActivity());

	}

	/**
	 * �ؼ���������ʾ�ķ���
	 * */
	private void viewGone(View view) {
		switch (view.getVisibility()) {
		case View.GONE:
			view.setVisibility(View.VISIBLE);
			break;
		case View.VISIBLE:
			view.setVisibility(View.GONE);
			break;

		default:
			break;
		}
	}

	/**
	 * SharePopupWindow��Item����¼�
	 * */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		switch (position) {
		case 0:// qq

			break;
		case 1:// sina

			break;
		case 2:// wechat

			break;

		default:
			break;
		}

	}
}
