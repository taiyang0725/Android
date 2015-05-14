package com.feicui.news.config;

/**
 * 这是一个网络访问地址的类
 * */
public class NetConf {
	/**
	 * 基本路径
	 * */
	public static final String BASE_URI = "http://192.168.253.7:8080/newsClient/";
	/**
	 * 2.1.1 新闻分类
	 * */
	public static final String NEWS_SORT = BASE_URI + "news_sort?";
	/**
	 * 2.1.2 新闻列表
	 * */
	public static final String NEWS_LIST = BASE_URI + "news_list?";
	/**
	 * 2.1.4 新闻内容(大图新闻)
	 * */
	public static final String NEWS_IMAGE = BASE_URI + "news_image?";

	/**
	 * 2.3.1 用户中心
	 * */
	public static final String USER_HOME = BASE_URI + "user_home?";
	/**
	 * 2.3.2 头像上传
	 * */
	public static final String USER_IMAGE = BASE_URI + "user_image?";
	/**
	 * 2.3.3 登陆
	 * */
	public static final String USER_LOGIN = BASE_URI + "user_login?";
	/**
	 * 2.3.4 注册
	 * */
	public static final String USER_REGISTER = BASE_URI + "user_register?";
	/**
	 * 2.3.5 找回密码
	 * */
	public static final String USER_FORGETPASS = BASE_URI + "user_forgetpass?";
	/**
	 * 2.2.1 发布评论
	 * */
	public static final String CMT_COMMIT = BASE_URI + "cmt_commit?";
	/**
	 * 2.2.2 显示评论
	 * */
	public static final String CMT_LIST = BASE_URI + "cmt_list ?";
	/**
	 * 2.2.3 评论数量
	 * */
	public static final String CMT_NUM = BASE_URI + "cmt_num?";
	/**
	 * 2.4.1 版本升级
	 * */
	public static final String UPDATE = BASE_URI + "update?";

}
