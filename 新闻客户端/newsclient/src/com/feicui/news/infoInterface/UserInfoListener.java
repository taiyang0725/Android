package com.feicui.news.infoInterface;

import java.io.File;

import android.content.Context;

import com.loopj.android.http.ResponseHandlerInterface;

/** 2.3 �û�����ģ��ӿ� */
public interface UserInfoListener {

	/**
	 * �û�����
	 * 
	 * @param context
	 *            �����Ķ���
	 * @param imei
	 *            �ֻ���ʾ��
	 * @param token
	 *            �û�����
	 */
	void getUserHome(Context context, String token,
			ResponseHandlerInterface responseHandler);

	/**
	 * ͷ���ϴ�
	 * 
	 * @param token
	 *            �û�����
	 * @param portrait
	 *            ͷ��
	 */
	void getUserImage(String token, File portrait,
			ResponseHandlerInterface responseHandler);

	/**
	 * ��½
	 * 
	 * @param context
	 *            �����Ķ���
	 * @param uid
	 *            �û���
	 * @param pwd
	 *            ����
	 * @param device
	 *            ��¼�豸
	 */
	void UserLogin(Context context, String uid, String pwd, String device,
			ResponseHandlerInterface responseHandler);

	/**
	 * ע��
	 * 
	 * @param context
	 *            �����Ķ���
	 * @param uid
	 *            �û���
	 * @param email
	 *            ����
	 * @param pwd
	 *            ����
	 */
	void UserRegister(Context context, String uid, String email, String pwd,
			ResponseHandlerInterface responseHandler);

	/**
	 * �һ�����
	 * 
	 * @param context
	 *            �����Ķ���
	 * @param email
	 *            ����
	 */
	void UserForgetpass(Context context, String email,
			ResponseHandlerInterface responseHandler);
}
