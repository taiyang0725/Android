package com.feicui.news.mode;

import java.util.ArrayList;

/**
 * 新闻分类实体类
 * 
 * 
 */
public class NewsSort {

	/** 分类号 */
	private String gid;
	/** 分类名 */
	private String group;
	/** 分类内容 */
	private ArrayList<SubGrp> subgrp;

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public ArrayList<SubGrp> getSubgrp() {
		return subgrp;
	}

	public void setSubgrp(ArrayList<SubGrp> subgrp) {
		this.subgrp = subgrp;
	}

}
