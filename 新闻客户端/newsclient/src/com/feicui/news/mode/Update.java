package com.feicui.news.mode;


/** 版本升级实体类 */
public class Update {

	/** 包名 */
	private String pkgName;
	/** 版本号 */
	private String version;
	/** 下载路径 */
	private String link;
	/** md5校验值 */
	private String md5;

	public String getPkgName() {
		return pkgName;
	}

	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

}
