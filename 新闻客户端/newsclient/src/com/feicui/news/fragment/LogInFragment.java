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
 * 显示登陆界面
 * */
public class LogInFragment extends BaseFragment implements OnClickListener,
		PlatformActionListener {
	/**
	 * 菜单布局
	 * */
	private View view;
	/** 返回按钮 */
	private ImageView imgBack;
	/** 注册按钮 */
	private Button btnSign;
	/** 登陆按钮 */
	private Button btnLog;
	/** 找回密码 */
	private TextView txtPwd;
	private OnLogInListener callBack;

	private ImageView imageView;

	private TextView txt;
	/** 用户名 */
	private EditText edtName;
	private String name;
	/** 密码 */
	private EditText edtPwd;
	private String pwd;

	/** 设置授权回调，用于判断是否进入注册 */
	private OnLoginListener signupListener;

	/** 设置授权回调，用于判断是否进入注册 */
	public void setOnLoginListener(OnLoginListener l) {
		this.signupListener = l;
	}

	/** 转圈加载符 */
	private ProgressDialog progressDialog;

	// 填写从短信SDK应用后台注册得到的APPKEY
	private static String APPKEY = "44d938b85bc8";
	// 填写从短信SDK应用后台注册得到的APPSECRET
	private static String APPSECRET = "9fe36d653ea4f0cc854673032b46338d";
	/** qq登陆 */
	private ImageView imgQq;
	/** sina登陆 */
	private ImageView imgSina;
	/** 微信登陆 */
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

	/** 登陆界面回调接口 */
	public interface OnLogInListener {
		/** 返回主界面 */
		void onFromMenu();

		/** 跳至注册界面 */
		void onLogInFromSignUp();

		/** 跳至个人中心界面 */
		void onFromMy();

		/** 跳至找回密码界面 */
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

	// 执行授权,获取用户信息

	private void authorize(Platform plat) {

		// 初始化sharesdk,具体集成步骤请看文档：

		plat.setPlatformActionListener(this);
		// 关闭SSO授权
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
			// 新浪微博
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

	/** 登陆方法 */
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
			// 取消授权
			Toast.makeText(getActivity(), R.string.auth_cancel,
					Toast.LENGTH_SHORT).show();
		}
			break;

		case 4: {
			// 授权成功
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
			// 授权失败
			Toast.makeText(getActivity(), R.string.auth_error,
					Toast.LENGTH_SHORT).show();
		}
			break;
		}
		return false;
	}

}
