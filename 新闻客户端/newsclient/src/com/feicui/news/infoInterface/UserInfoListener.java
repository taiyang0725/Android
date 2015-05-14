package com.feicui.news.infoInterface;

import java.io.File;

import android.content.Context;

import com.loopj.android.http.ResponseHandlerInterface;

/** 2.3 用户管理模块接口 */
public interface UserInfoListener {

	/**
	 * 用户中心
	 * 
	 * @param context
	 *            上下文对象
	 * @param imei
	 *            手机标示符
	 * @param token
	 *            用户令牌
	 */
	void getUserHome(Context context, String token,
			ResponseHandlerInterface responseHandler);

	/**
	 * 头像上传
	 * 
	 * @param token
	 *            用户令牌
	 * @param portrait
	 *            头像
	 */
	void getUserImage(String token, File portrait,
			ResponseHandlerInterface responseHandler);

	/**
	 * 登陆
	 * 
	 * @param context
	 *            上下文对象
	 * @param uid
	 *            用户名
	 * @param pwd
	 *            密码
	 * @param device
	 *            登录设备
	 */
	void UserLogin(Context context, String uid, String pwd, String device,
			ResponseHandlerInterface responseHandler);

	/**
	 * 注册
	 * 
	 * @param context
	 *            上下文对象
	 * @param uid
	 *            用户名
	 * @param email
	 *            邮箱
	 * @param pwd
	 *            密码
	 */
	void UserRegister(Context context, String uid, String email, String pwd,
			ResponseHandlerInterface responseHandler);

	/**
	 * 找回密码
	 * 
	 * @param context
	 *            上下文对象
	 * @param email
	 *            邮箱
	 */
	void UserForgetpass(Context context, String email,
			ResponseHandlerInterface responseHandler);
}
