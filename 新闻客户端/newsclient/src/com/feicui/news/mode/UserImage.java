package com.feicui.news.mode;

/** 头像上传 */
public class UserImage {
	/** 手机令牌 */
	private String token;
	/** 上传状态 */
	private String result;
	/** 上传说明 */
	private String explain;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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
