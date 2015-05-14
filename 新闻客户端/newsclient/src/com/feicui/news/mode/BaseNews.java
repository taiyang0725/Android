package com.feicui.news.mode;

/**
 * 这是一个新闻基本实体类
 * 
 * @param <T>
 */
public class BaseNews<T> {
	/** 状态码 */
	private String status;
	/** 状态说明 */
	private String message;
	/** 数据集合 */
	private T data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
