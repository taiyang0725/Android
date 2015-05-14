package com.feicui.news.infoInterface;

import java.util.HashMap;

import com.feicui.news.mode.UserHome;

public interface OnLoginListener {
	/** 授权完成调用此接口，返回授权数据，如果需要注册，则返回true */
	public boolean onSignin(String platform, HashMap<String, Object> res);

	/** 填写完注册信息后调用此接口，返回true表示数据合法，注册页面可以关�? */
	public boolean onSignUp(UserHome info);

}
