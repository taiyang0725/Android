package com.feicui.news.mode;

import java.io.Serializable;

/**
 * 这是一个界面实体模型类
 * */
public class UIEntity implements Serializable {
	/**
	 * 图标
	 * */
	private int icon;
	/**
	 * 名称
	 * */
	private String name;

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
