package com.feicui.news.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;

import android.content.res.XmlResourceParser;
import android.util.Log;

public class PullParserHelper {
	private final static String TAG = "PullParserHelper";

	/**
	 * 解析XML文件
	 * 
	 * @param xml
	 * @return
	 */
	public static Map<String, String> listparserXml(XmlResourceParser xml) {
		Log.i(TAG, "==listparserXml()");
		Map<String, String> map = null;
		try {
			XmlPullParser pullParser = xml;
			int event = pullParser.getEventType();
			String provinceName = null;
			String cityName = null;
			boolean firstFlag = true;// 判断是不是第一次读取地区名
			while (event != 1) {
				switch (event) {
				case 0:
					// list = new ArrayList<String>();
					map = new HashMap<String, String>();
					break;
				case 2:
					if ("pn".equals(pullParser.getName())) {
						provinceName = pullParser.nextText();
					}
					if ("cn".equals(pullParser.getName())) {
						firstFlag = true;
						cityName = pullParser.nextText();
					}
					if ("d".equals(pullParser.getName())) {
						String id = pullParser.getAttributeValue(0);
						String areaName = pullParser.nextText();
						if (firstFlag) {// 是第一次保存地区名和id
							areaName = areaName + "-" + provinceName;
						} else {
							areaName = areaName + "-" + cityName + "-"
									+ provinceName;
						}
						map.put(areaName, id);
						// list.add(cityName);
						firstFlag = false;
					}
					break;
				}
				event = pullParser.next();
			}
		} catch (Exception e) {
			Log.i(TAG, "==解析异常");
			e.printStackTrace();
		}
		Log.i(TAG, "map==" + map);
		return map;
	}

	/**
	 * 得到省的解析结果
	 * 
	 * @param xml
	 * @return
	 */
	public static List<Map<String, String>> getListProvinceByXml(
			XmlResourceParser pullParser) {
		Log.i(TAG, "==getMapProvinceByXml()");
		List<Map<String, String>> list = null;
		Map<String, String> map = null;
		try {
			int event = pullParser.getEventType();
			while (event != 1) {
				switch (event) {
				case 0:
					// list = new ArrayList<String>();
					list = new ArrayList<Map<String, String>>();
					break;
				case 2:
					if ("p".equals(pullParser.getName())) {
						map = new HashMap<String, String>();
						String id = pullParser.getAttributeValue(0);
						pullParser.next();
						String value = pullParser.nextText();
						map.put("id", id);
						map.put("provinceName", value);
						list.add(map);
					}
					break;
				}
				event = pullParser.next();
			}
		} catch (Exception e) {
			Log.i(TAG, "==解析异常");
			e.printStackTrace();
		}
		Log.i(TAG, "list==" + list);
		return list;
	}

	public static List<Map<String, String>> getListCityByXml(
			XmlResourceParser pullParser, String provinceId) {
		Log.i(TAG, "==getListCityByXml()");
		List<Map<String, String>> list = null;
		Map<String, String> map = null;
		try {
			int event = pullParser.getEventType();
			aa: while (event != 1) {
				switch (event) {
				case 0:
					list = new ArrayList<Map<String, String>>();
					break;
				case 2:
					if ("c".equals(pullParser.getName())) {
						if (provinceId.equals(pullParser.getAttributeValue(0)
								.substring(0, 2))) {
							map = new HashMap<String, String>();
							String id = pullParser.getAttributeValue(0);
							pullParser.next();
							String value = pullParser.nextText();
							map.put("id", id);
							map.put("cityName", value);
							list.add(map);
						}
					}
					break;
				case 3:
					if ("p".equals(pullParser.getName()) && map != null) {
						break aa;
					}
					break;
				}

				event = pullParser.next();
			}
		} catch (Exception e) {
			Log.i(TAG, "==解析异常");
			e.printStackTrace();
		}
		Log.i(TAG, "list==" + list);
		return list;
	}

	public static List<Map<String, String>> getListAreaByXml(
			XmlResourceParser pullParser, String cityId) {
		Log.i(TAG, "==getListCityByXml()");
		List<Map<String, String>> list = null;
		Map<String, String> map = null;
		try {
			int event = pullParser.getEventType();
			aa: while (event != 1) {
				switch (event) {
				case 0:
					break;
				case 2:
					if ("c".equals(pullParser.getName())) {
						if (cityId.equals(pullParser.getAttributeValue(0))) {
							list = new ArrayList<Map<String, String>>();
						}
					}
					if (list == null) {
						break;
					}
					if ("d".equals(pullParser.getName())) {
						map = new HashMap<String, String>();
						String id = pullParser.getAttributeValue(0);
						String value = pullParser.nextText();
						map.put("id", id);
						map.put("areaName", value);
						list.add(map);
					}
					break;
				case 3:
					if ("c".equals(pullParser.getName()) && list != null) {
						break aa;
					}
					break;
				}
				event = pullParser.next();
			}
		} catch (Exception e) {
			Log.i(TAG, "==解析异常");
			e.printStackTrace();
		}
		Log.i(TAG, "list==" + list);
		return list;
	}
}
