package com.feicui.news.data;

import java.util.ArrayList;

import android.content.Context;

import com.feicui.news.R;
import com.feicui.news.mode.UIEntity;

/**
 * 提供页面基本数据
 * */
public class PageBasicData {

	/**
	 * 提供PopupWinder菜单图片与名字
	 * */
	public static ArrayList<UIEntity> getPopup(Context context) {

		ArrayList<UIEntity> list = new ArrayList<UIEntity>();

		int[] icon = { R.drawable.weather_forecast, R.drawable.richscan,
				R.drawable.check_for_updates, R.drawable.feedback,
				R.drawable.popup_back };

		String[] name = context.getResources().getStringArray(R.array.popup);
		for (int i = 0; i < icon.length; i++) {

			UIEntity entity = new UIEntity();
			entity.setIcon(icon[i]);
			entity.setName(name[i]);
			list.add(entity);
		}

		return list;

	}

	/**
	 * 提供横向ListView文字
	 * */
	public static ArrayList<UIEntity> getHLst(Context context) {

		ArrayList<UIEntity> list = new ArrayList<UIEntity>();

		String[] name = context.getResources().getStringArray(R.array.hlst);
		for (int i = 0; i < name.length; i++) {
			UIEntity entity = new UIEntity();

			entity.setName(name[i]);
			list.add(entity);

		}

		return list;
	}

	/**
	 * 图片
	 * */
	private static String[] pic;

	public static String[] getPic() {
		// pic = new String[] {
		// "http://img0.bdstatic.com/static/common/widget/search_box_search/logo/logo_3b6de4c.png",
		// "http://img0.bdstatic.com/static/common/widget/search_box_search/logo/logo_3b6de4c.png",
		// "http://img0.bdstatic.com/static/common/widget/search_box_search/logo/logo_3b6de4c.png",
		// "http://img0.bdstatic.com/static/common/widget/search_box_search/logo/logo_3b6de4c.png",
		// "http://img0.bdstatic.com/static/common/widget/search_box_search/logo/logo_3b6de4c.png",
		// "http://img0.bdstatic.com/static/common/widget/search_box_search/logo/logo_3b6de4c.png",
		// "http://img0.bdstatic.com/static/common/widget/search_box_search/logo/logo_3b6de4c.png",
		// "http://img0.bdstatic.com/static/common/widget/search_box_search/logo/logo_3b6de4c.png",
		// "http://img0.bdstatic.com/static/common/widget/search_box_search/logo/logo_3b6de4c.png",
		// "http://img0.bdstatic.com/static/common/widget/search_box_search/logo/logo_3b6de4c.png"
		// };
		pic = new String[] {
				"http://image.baidu.com/detail/newindex?col=%E6%B1%BD%E8%BD%A6&tag=%E5%85%A8%E9%83%A8&pn=0&pid=9571632436&aid=&user_id=906962678&setid=-1&sort=0&newsPn=&star=&fr=&from=1",
				"http://image.baidu.com/detail/newindex?col=%E6%B1%BD%E8%BD%A6&tag=%E5%85%A8%E9%83%A8&pn=1&pid=9348303541&aid=&user_id=906962678&setid=-1&sort=0&newsPn=&star=&fr=&from=1",
				"http://image.baidu.com/detail/newindex?col=%E6%B1%BD%E8%BD%A6&tag=%E5%85%A8%E9%83%A8&pn=2&pid=9542874160&aid=&user_id=906962678&setid=-1&sort=0&newsPn=&star=&fr=&from=1",
				"http://img0.bdstatic.com/static/common/widget/search_box_search/logo/logo_3b6de4c.png",
				"http://image.baidu.com/detail/newindex?col=%E6%B1%BD%E8%BD%A6&tag=%E5%85%A8%E9%83%A8&pn=3&pid=9619187403&aid=&user_id=906962678&setid=-1&sort=0&newsPn=&star=&fr=&from=1",
				"http://image.baidu.com/detail/newindex?col=%E6%B1%BD%E8%BD%A6&tag=%E5%85%A8%E9%83%A8&pn=4&pid=9543408995&aid=&user_id=906962678&setid=-1&sort=0&newsPn=&star=&fr=&from=1",
				"http://image.baidu.com/detail/newindex?col=%E6%B1%BD%E8%BD%A6&tag=%E5%85%A8%E9%83%A8&pn=8&pid=9558741583&aid=&user_id=906962678&setid=-1&sort=0&newsPn=&star=&fr=&from=1",
				"http://image.baidu.com/detail/newindex?col=%E6%B1%BD%E8%BD%A6&tag=%E5%85%A8%E9%83%A8&pn=9&pid=9529113825&aid=&user_id=906962678&setid=-1&sort=0&newsPn=&star=&fr=&from=1",
				"http://image.baidu.com/detail/newindex?col=%E6%B1%BD%E8%BD%A6&tag=%E5%85%A8%E9%83%A8&pn=15&pid=9349930124&aid=&user_id=906962678&setid=-1&sort=0&newsPn=&star=&fr=&from=1",
				"http://image.baidu.com/detail/newindex?col=%E6%B1%BD%E8%BD%A6&tag=%E5%85%A8%E9%83%A8&pn=23&pid=9903037961&aid=&user_id=906962678&setid=3500&sort=0&newsPn=&star=&fr=&from=1",
				"http://image.baidu.com/detail/newindex?col=%E6%B1%BD%E8%BD%A6&tag=%E5%85%A8%E9%83%A8&pn=23&pid=9903037961&aid=&user_id=906962678&setid=3500&sort=0&newsPn=&star=&fr=&from=1" };
		return pic;
	}

	public static ArrayList<UIEntity> getShare(Context context) {

		ArrayList<UIEntity> list = new ArrayList<UIEntity>();
		int[] icon = { R.drawable.social_share_icon_qq,
				R.drawable.social_share_icon_sina,
				R.drawable.social_share_icon_wechat };
		String[] name = context.getResources().getStringArray(R.array.share);
		for (int i = 0; i < icon.length; i++) {

			UIEntity entity = new UIEntity();
			entity.setIcon(icon[i]);
			entity.setName(name[i]);
			list.add(entity);
		}

		return list;
	}
}
