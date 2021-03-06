package com.feicui.news.mode;

/**
 * 新闻列表实体类
 * */
public class NewsList {
	/** 类型标示 */
	private String type;
	/** 新闻编号 */
	private String nid;
	/** 新闻时间 */
	private String stamp;
	/** 图标路径 */
	private String icon;
	/** 新闻标题 */
	private String title;
	/** 新闻摘要 */
	private String summary;
	/** 新闻链接 */
	private String link;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getStamp() {
		return stamp;
	}

	public void setStamp(String stamp) {
		this.stamp = stamp;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
