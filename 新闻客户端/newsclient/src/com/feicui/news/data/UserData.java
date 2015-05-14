package com.feicui.news.data;

import java.io.File;

import org.apache.http.Header;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.feicui.news.manager.UserManager;
import com.feicui.news.mode.BaseNews;
import com.feicui.news.mode.UserForgetpass;
import com.feicui.news.mode.UserHome;
import com.feicui.news.mode.UserImage;
import com.feicui.news.mode.UserLogin;
import com.feicui.news.mode.UserRegister;
import com.feicui.news.util.LogUtil;
import com.feicui.news.util.ShareUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;

/** 用户数据类 */
public class UserData {

	/** 注册 */

	public static void getSignUp(final Context context, String uid,
			String email, String pwd, final Handler handler) {
		UserManager manager = new UserManager();
		manager.UserRegister(context, uid, email, pwd,
				new TextHttpResponseHandler() {

					@Override
					public void onSuccess(int arg0, Header[] arg1, String arg2) {
						Gson gson = new Gson();

						BaseNews<UserRegister> baseNews = gson.fromJson(arg2,
								new TypeToken<BaseNews<UserRegister>>() {
								}.getType());
						if (baseNews.getStatus().equals("0")) {

							UserRegister register = baseNews.getData();

							ShareUtil.setShare(context, register.getToken(),
									"Token");
							ShareUtil.setShare(context, register.getEmail(),
									"E-mail");
							ShareUtil.setOnclick(context, register.getResult()
									.equals("0"), "LOGGING_STATUS");
							Toast.makeText(context, register.getExplain(),
									Toast.LENGTH_SHORT).show();
							Message message = new Message();
							message.what = 1;
							message.obj = register.getResult();
							handler.sendMessage(message);
						}

					}

					@Override
					public void onFailure(int arg0, Header[] arg1, String arg2,
							Throwable arg3) {

						handler.sendEmptyMessage(2);

					}
				});

	}

	/** 登陆 */
	public static void getLogIn(final Context context, String uid, String pwd,
			String device, final Handler handler) {
		UserManager manager = new UserManager();
		manager.UserLogin(context, uid, pwd, device,
				new TextHttpResponseHandler() {

					@Override
					public void onSuccess(int arg0, Header[] arg1, String arg2) {
						Gson gson = new Gson();

						BaseNews<UserLogin> baseNews = gson.fromJson(arg2,
								new TypeToken<BaseNews<UserLogin>>() {
								}.getType());
						if (baseNews.getStatus().equals("0")) {

							UserLogin log = baseNews.getData();

							Toast.makeText(context, log.getExplain(),
									Toast.LENGTH_SHORT).show();
							ShareUtil.setOnclick(context, log.getResult()
									.equals("0"), "LOGGING_STATUS");
							ShareUtil.setShare(context, log.getToken(), "Token");
							Message message = new Message();
							message.what = 1;
							message.obj = log.getResult();
							handler.sendMessage(message);

						} else {

							handler.sendEmptyMessage(1);
							Toast.makeText(context, arg2, Toast.LENGTH_SHORT)
									.show();
						}

					}

					@Override
					public void onFailure(int arg0, Header[] arg1, String arg2,
							Throwable arg3) {
						handler.sendEmptyMessage(2);

					}
				});

	}

	// private static UserHome userHome;

	/** 用回中心 */
	public static void getUserHome(final Context context, String token,
			final Handler handler) {
		UserManager manager = new UserManager();

		manager.getUserHome(context, token, new TextHttpResponseHandler() {

			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				Gson gson = new Gson();

				BaseNews<com.feicui.news.mode.UserHome> baseNews = gson
						.fromJson(arg2, new TypeToken<BaseNews<UserHome>>() {
						}.getType());
				if (baseNews.getStatus().equals("0")) {

					UserHome userHome = baseNews.getData();

					ShareUtil.setShare(context, userHome.getUid(), "uid_name");

					Message message = new Message();
					message.what = 1;
					message.obj = userHome;
					handler.sendMessage(message);

				}
			}

			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2,
					Throwable arg3) {

				handler.sendEmptyMessage(2);

			}
		});

	}

	/** 头像上传 */
	public static void getUserImage(final Context context, String token,
			File portrait, final Handler handler) {
		UserManager manager = new UserManager();
		manager.getUserImage(token, portrait, new TextHttpResponseHandler() {

			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				LogUtil.d("TUPIAN" + arg2);
				Gson gson = new Gson();
				BaseNews<UserImage> baseNews = gson.fromJson(arg2,
						new TypeToken<BaseNews<UserImage>>() {
						}.getType());
				if (baseNews.getStatus().equals("0")) {
					UserImage userImage = baseNews.getData();

					Toast.makeText(context, userImage.getExplain(),
							Toast.LENGTH_SHORT).show();
					Message message = new Message();
					message.what = 3;
					message.obj = userImage;
					handler.sendMessage(message);
				}

			}

			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2,
					Throwable arg3) {
				handler.sendEmptyMessage(2);
			}
		});

	}

	/** 找回密码 */

	public static void getPassword(final Context context, String email,
			final Handler handler) {
		UserManager manager = new UserManager();
		manager.UserForgetpass(context, email, new TextHttpResponseHandler() {

			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				Gson gson = new Gson();
				BaseNews<UserForgetpass> baseNews = gson.fromJson(arg2,
						new TypeToken<BaseNews<UserForgetpass>>() {
						}.getType());
				if (baseNews.getStatus().equals("0")) {

					UserForgetpass userForgetpass = baseNews.getData();

					Toast.makeText(context, userForgetpass.getExplain(),
							Toast.LENGTH_SHORT).show();
					Message message = new Message();
					message.what = 1;
					message.obj = userForgetpass.getResult();
					handler.sendMessage(message);

				}

			}

			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2,
					Throwable arg3) {
				handler.sendEmptyMessage(2);

			}
		});

	}
}
