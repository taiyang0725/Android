package com.feicui.news.fragment;

import java.util.HashMap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

import com.feicui.news.BaseFragment;
import com.feicui.news.R;
import com.feicui.news.data.UserData;
import com.feicui.news.infoInterface.OnLoginListener;

/**
 * ��ʾ��½����
 * */
public class LogInFragment extends BaseFragment implements OnClickListener,
		PlatformActionListener {
	/**
	 * �˵�����
	 * */
	private View view;
	/** ���ذ�ť */
	private ImageView imgBack;
	/** ע�ᰴť */
	private Button btnSign;
	/** ��½��ť */
	private Button btnLog;
	/** �һ����� */
	private TextView txtPwd;
	private OnLogInListener callBack;

	private ImageView imageView;

	private TextView txt;
	/** �û��� */
	private EditText edtName;
	private String name;
	/** ���� */
	private EditText edtPwd;
	private String pwd;

	/** ������Ȩ�ص��������ж��Ƿ����ע�� */
	private OnLoginListener signupListener;

	/** ������Ȩ�ص��������ж��Ƿ����ע�� */
	public void setOnLoginListener(OnLoginListener l) {
		this.signupListener = l;
	}

	/** תȦ���ط� */
	private ProgressDialog progressDialog;

	// ��д�Ӷ���SDKӦ�ú�̨ע��õ���APPKEY
	private static String APPKEY = "44d938b85bc8";
	// ��д�Ӷ���SDKӦ�ú�̨ע��õ���APPSECRET
	private static String APPSECRET = "9fe36d653ea4f0cc854673032b46338d";
	/** qq��½ */
	private ImageView imgQq;
	/** sina��½ */
	private ImageView imgSina;
	/** ΢�ŵ�½ */
	private ImageView imgWX;
	private String result;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				String result = (String) msg.obj;
				if (result != null) {
					if (result.equals("0")) {

						callBack.onFromMy();
					}
				}
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

	public LogInFragment(ImageView imageView, TextView txt) {

		this.imageView = imageView;
		this.txt = txt;

	}

	/** ��½����ص��ӿ� */
	public interface OnLogInListener {
		/** ���������� */
		void onFromMenu();

		/** ����ע����� */
		void onLogInFromSignUp();

		/** �����������Ľ��� */
		void onFromMy();

		/** �����һ�������� */
		void onFromFindPwd();

	}

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);
		try {
			callBack = (OnLogInListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnHeadlineSelectedListener");
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.log_up, null);
		initView(view);
		return view;
	}

	private void initView(View view) {
		imageView.setBackgroundResource(R.drawable.arrow_white_left);
		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				callBack.onFromMenu();

			}
		});

		txt.setText(R.string.log_in);

		edtName = (EditText) view.findViewById(R.id.edt_log_in);

		edtPwd = (EditText) view.findViewById(R.id.edt_log_in_password);

		txtPwd = (TextView) view.findViewById(R.id.txt_find_password);
		txtPwd.setOnClickListener(this);

		btnLog = (Button) view.findViewById(R.id.btn_log_in);
		btnLog.setOnClickListener(this);

		btnSign = (Button) view.findViewById(R.id.btn_sign_up);
		btnSign.setOnClickListener(this);

		imgQq = (ImageView) view.findViewById(R.id.img_share_qq);
		imgQq.setOnClickListener(this);

		imgSina = (ImageView) view.findViewById(R.id.img_share_sina);
		imgSina.setOnClickListener(this);

		imgWX = (ImageView) view.findViewById(R.id.img_share_wechat);
		imgWX.setOnClickListener(this);

	}

	// ִ����Ȩ,��ȡ�û���Ϣ

	private void authorize(Platform plat) {

		// ��ʼ��sharesdk,���弯�ɲ����뿴�ĵ���

		plat.setPlatformActionListener(this);
		// �ر�SSO��Ȩ
		plat.SSOSetting(true);
		plat.showUser(null);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_log_in:
			logIn();
			break;
		case R.id.btn_sign_up:
			callBack.onLogInFromSignUp();
			break;
		case R.id.txt_find_password:
			callBack.onFromFindPwd();
			break;
		case R.id.img_share_qq:
			Platform qq = ShareSDK.getPlatform(QQ.NAME);
			authorize(qq);
			break;
		case R.id.img_share_sina:
			// ����΢��
			Platform sina = ShareSDK.getPlatform(SinaWeibo.NAME);
			authorize(sina);
			break;
		case R.id.img_share_wechat:
			Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
			authorize(wechat);
			break;

		default:
			break;
		}

	}

	/** ��½���� */
	private void logIn() {

		name = edtName.getText().toString();
		pwd = edtPwd.getText().toString();

		if (TextUtils.isEmpty(name.trim())) {
			edtName.setError(getResources().getString(R.string.null_null));
		} else if (TextUtils.isEmpty(pwd.trim())) {
			edtPwd.setError(getResources().getString(R.string.null_null));
		} else {
			UserData.getLogIn(getActivity(), name, pwd, "0", handler);
			progressDialog = ProgressDialog.show(getActivity(), null,
					getResources().getString(R.string.log_in_1));

		}
	}

	/**
	 * PlatformActionListener
	 * */
	@Override
	public void onCancel(Platform platform, int action) {
		if (action == Platform.ACTION_USER_INFOR) {
			handler.sendEmptyMessage(3);
		}

	}

	@Override
	public void onComplete(Platform platform, int action,
			HashMap<String, Object> res) {
		if (action == Platform.ACTION_USER_INFOR) {
			Message msg = new Message();
			msg.what = 4;
			msg.obj = new Object[] { platform.getName(), res };
			handler.sendMessage(msg);
		}

	}

	@Override
	public void onError(Platform platform, int action, Throwable t) {
		if (action == Platform.ACTION_USER_INFOR) {
			handler.sendEmptyMessage(4);
		}
		t.printStackTrace();

	}

	@SuppressWarnings("unchecked")
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
		case 3: {
			// ȡ����Ȩ
			Toast.makeText(getActivity(), R.string.auth_cancel,
					Toast.LENGTH_SHORT).show();
		}
			break;

		case 4: {
			// ��Ȩ�ɹ�
			Toast.makeText(getActivity(), R.string.auth_complete,
					Toast.LENGTH_SHORT).show();
			Object[] objs = (Object[]) msg.obj;
			String platform = (String) objs[0];
			HashMap<String, Object> res = (HashMap<String, Object>) objs[1];
			if (signupListener != null
					&& signupListener.onSignin(platform, res)) {
				// SignupPage signupPage = new SignupPage();
				// signupPage.setOnLoginListener(signupListener);
				// signupPage.setPlatform(platform);
				// signupPage.show(getActivity(), null);
				MyFragment fragment = new MyFragment();
				fragment.setPlatform(platform);
			}
		}
			break;
		case 5: {
			// ��Ȩʧ��
			Toast.makeText(getActivity(), R.string.auth_error,
					Toast.LENGTH_SHORT).show();
		}
			break;
		}
		return false;
	}

}
