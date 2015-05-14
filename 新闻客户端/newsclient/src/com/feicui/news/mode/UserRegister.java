package com.feicui.news.mode;

/** 注册实体类 */
public class UserRegister {
	/** 用户名 */
	private String uid;
	/** 邮箱 */
	private String email;
	/** 密码 */
	private String pwd;
	/** 版本 */
	private String ver;
	/** 注册状态 */
	private String result;
	/** 注册说明 */
	private String explain;
	/** 手机令牌 */
	private String token;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
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
