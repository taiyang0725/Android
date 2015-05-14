package com.feicui.news.mode;

/**
 * ����һ�����Ż���ʵ����
 * 
 * @param <T>
 */
public class BaseNews<T> {
	/** ״̬�� */
	private String status;
	/** ״̬˵�� */
	private String message;
	/** ���ݼ��� */
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
