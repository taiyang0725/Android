package com.feicui.news.mode;

/** 登陆 */
public class UserLogin {
	/** 版本号 */
	private String ver;
	/** 用户名 */
	private String uid;
	/** 密码 */
	private String pwd;
	/** 登陆状态 */
	private String result;
	/** 登陆成功 */
	private String explain;
	/** 手机令牌 */
	private String token;

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
