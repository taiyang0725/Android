package com.feicui.news.mode;


/** ��ʾ���� */
public class CmtList {

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStamp() {
		return stamp;
	}

	public void setStamp(String stamp) {
		this.stamp = stamp;
	}

	/** ���۱�� */
	private String cid;
	/** ���������� */
	private String uid;
	/** �û�ͷ������ */
	private String portrait;
	/** �������� */
	private String content;

	/** ����ʱ�� */
	private String stamp;

}
