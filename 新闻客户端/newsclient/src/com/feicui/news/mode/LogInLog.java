package com.feicui.news.mode;

/** 登陆日志实体类 */
public class LogInLog {
	/** 登录时间 */
	private String time;
	/** 登陆地址 */
	private String address;
	/** 登录设备 */
	private String device;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

}
