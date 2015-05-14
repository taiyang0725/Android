package com.feicui.news.mode;

/**
 * 发布评论实体类
 * */
public class CmtCommit {
	/** 新闻ID */
	private String nid;
	/** 版本 */
	private String ver;
	/** 用户令牌 */
	private String token;
	/** 手机标示符 */
	private String imei;
	/** 评论内容 */
	private String ctx;
	/** 回复状态 */
	private String result;
	/** 回复说明 */
	private String explain;

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getCtx() {
		return ctx;
	}

	public void setCtx(String ctx) {
		this.ctx = ctx;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

}
