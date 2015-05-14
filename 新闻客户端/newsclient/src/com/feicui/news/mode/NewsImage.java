package com.feicui.news.mode;

/**
 * 新闻内容实体类
 * */
public class NewsImage {
	/** 新闻图片链接 */
	private String image;
	/** 图片说明 */
	private String introduct;
	/** 新闻编号 */
	private String nid;
	/** 图标路径 */
	private String icon;
	/** 新闻标题 */
	private String title;
	/** 新闻摘要 */
	private String summary;
	/** 新闻链接 */
	private String link;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getIntroduct() {
		return introduct;
	}

	public void setIntroduct(String introduct) {
		this.introduct = introduct;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
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
