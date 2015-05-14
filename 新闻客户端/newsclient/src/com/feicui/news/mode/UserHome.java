package com.feicui.news.mode;

import java.util.ArrayList;

/** 用户中心 实体类 */
public class UserHome {
	/** 用户名 */
	private String uid;
	/** 用户图标 */
	private String portrait;
	/** 用户积分票总数 */
	private String integration;
	/** 评论总数 */
	private String comnum;
	/** 登录信息 */
	private ArrayList<LogInLog> loginlog;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public String getIntegration() {
		return integration;
	}

	public void setIntegration(String integration) {
		this.integration = integration;
	}

	public String getComnum() {
		return comnum;
	}

	public void setComnum(String comnum) {
		this.comnum = comnum;
	}

	public ArrayList<LogInLog> getLoginlog() {
		return loginlog;
	}

	public void setLoginlog(ArrayList<LogInLog> loginlog) {
		this.loginlog = loginlog;
	}

}
