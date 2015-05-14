package com.feicui.news.mode;


/** 显示评论 */
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

	/** 评论编号 */
	private String cid;
	/** 评论者名字 */
	private String uid;
	/** 用户头像链接 */
	private String portrait;
	/** 评论内容 */
	private String content;

	/** 评论时间 */
	private String stamp;

}
