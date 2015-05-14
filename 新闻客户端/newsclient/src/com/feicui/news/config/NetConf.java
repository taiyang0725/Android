package com.feicui.news.config;

/**
 * ����һ��������ʵ�ַ����
 * */
public class NetConf {
	/**
	 * ����·��
	 * */
	public static final String BASE_URI = "http://192.168.253.7:8080/newsClient/";
	/**
	 * 2.1.1 ���ŷ���
	 * */
	public static final String NEWS_SORT = BASE_URI + "news_sort?";
	/**
	 * 2.1.2 �����б�
	 * */
	public static final String NEWS_LIST = BASE_URI + "news_list?";
	/**
	 * 2.1.4 ��������(��ͼ����)
	 * */
	public static final String NEWS_IMAGE = BASE_URI + "news_image?";

	/**
	 * 2.3.1 �û�����
	 * */
	public static final String USER_HOME = BASE_URI + "user_home?";
	/**
	 * 2.3.2 ͷ���ϴ�
	 * */
	public static final String USER_IMAGE = BASE_URI + "user_image?";
	/**
	 * 2.3.3 ��½
	 * */
	public static final String USER_LOGIN = BASE_URI + "user_login?";
	/**
	 * 2.3.4 ע��
	 * */
	public static final String USER_REGISTER = BASE_URI + "user_register?";
	/**
	 * 2.3.5 �һ�����
	 * */
	public static final String USER_FORGETPASS = BASE_URI + "user_forgetpass?";
	/**
	 * 2.2.1 ��������
	 * */
	public static final String CMT_COMMIT = BASE_URI + "cmt_commit?";
	/**
	 * 2.2.2 ��ʾ����
	 * */
	public static final String CMT_LIST = BASE_URI + "cmt_list ?";
	/**
	 * 2.2.3 ��������
	 * */
	public static final String CMT_NUM = BASE_URI + "cmt_num?";
	/**
	 * 2.4.1 �汾����
	 * */
	public static final String UPDATE = BASE_URI + "update?";

}
