package com.feicui.news.mode;

import java.util.ArrayList;

/** �û����� ʵ���� */
public class UserHome {
	/** �û��� */
	private String uid;
	/** �û�ͼ�� */
	private String portrait;
	/** �û�����Ʊ���� */
	private String integration;
	/** �������� */
	private String comnum;
	/** ��¼��Ϣ */
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
