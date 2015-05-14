package com.feicui.news.manager;

import java.io.File;
import java.io.FileNotFoundException;

import android.content.Context;

import com.feicui.news.config.NetConf;
import com.feicui.news.infoInterface.UserInfoListener;
import com.feicui.news.util.LogUtil;
import com.feicui.news.util.SystemUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

public class UserManager implements UserInfoListener {

	@Override
	public void getUserHome(Context context, String token,
			ResponseHandlerInterface responseHandler) {

		AsyncHttpClient client = new AsyncHttpClient();

		StringBuffer url = new StringBuffer(NetConf.USER_HOME);
		url.append("ver=").append(SystemUtil.getVersionName0(context));
		url.append("&");
		url.append("imei=").append(SystemUtil.getImei(context));
		url.append("&");
		url.append("token =").append(token);
		LogUtil.d("个人中心" + url);
		client.get(url.toString(), responseHandler);

	}

	@Override
	public void getUserImage(String token, File portrait,
			ResponseHandlerInterface responseHandler) {

		AsyncHttpClient client = new AsyncHttpClient();

		StringBuffer url = new StringBuffer(NetConf.USER_IMAGE);
		url.append("token=").append(token);

		RequestParams params = new RequestParams();
		try {
			params.put("portrait", portrait);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		client.post(url.toString(), params, responseHandler);

	}

	@Override
	public void UserLogin(Context context, String uid, String pwd,
			String device, ResponseHandlerInterface responseHandler) {
		AsyncHttpClient client = new AsyncHttpClient();

		StringBuffer url = new StringBuffer(NetConf.USER_LOGIN);
		url.append("ver=").append(SystemUtil.getVersionName0(context));
		url.append("&");
		url.append("uid=").append(uid);
		url.append("&");
		url.append("pwd=").append(pwd);
		url.append("&");
		url.append("device=").append(device);
		LogUtil.d("登陆" + url);
		client.get(url.toString(), responseHandler);

	}

	@Override
	public void UserRegister(Context context, String uid, String email,
			String pwd, ResponseHandlerInterface responseHandler) {
		AsyncHttpClient client = new AsyncHttpClient();

		StringBuffer url = new StringBuffer(NetConf.USER_REGISTER);
		url.append("ver=").append(SystemUtil.getVersionName0(context));
		url.append("&");
		url.append("uid=").append(uid);
		url.append("&");
		url.append("email=").append(email);
		url.append("&");
		url.append("pwd=").append(pwd);
		LogUtil.d("注册" + url);
		client.get(url.toString(), responseHandler);

	}

	@Override
	public void UserForgetpass(Context context, String email,
			ResponseHandlerInterface responseHandler) {
		AsyncHttpClient client = new AsyncHttpClient();

		StringBuffer url = new StringBuffer(NetConf.USER_FORGETPASS);
		url.append("ver=").append(SystemUtil.getVersionName0(context));

		url.append("&");
		url.append("email=").append(email);
		LogUtil.d("找回密码" + url);
		client.get(url.toString(), responseHandler);
	}

}
