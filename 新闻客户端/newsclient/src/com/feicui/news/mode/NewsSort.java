package com.feicui.news.mode;

import java.util.ArrayList;

/**
 * ���ŷ���ʵ����
 * 
 * 
 */
public class NewsSort {

	/** ����� */
	private String gid;
	/** ������ */
	private String group;
	/** �������� */
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
