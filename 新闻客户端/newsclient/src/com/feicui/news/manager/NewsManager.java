package com.feicui.news.manager;

import android.content.Context;

import com.feicui.news.config.NetConf;
import com.feicui.news.infoInterface.NewsInfoListener;
import com.feicui.news.util.LogUtil;
import com.feicui.news.util.SystemUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.ResponseHandlerInterface;

/**
 * 新闻管理者
 * */
public class NewsManager implements NewsInfoListener {

	@Override
	public void getNewsSort(Context context,
			ResponseHandlerInterface responseHandler) {

		AsyncHttpClient client = new AsyncHttpClient();
		StringBuffer url = new StringBuffer(NetConf.NEWS_SORT);
		url.append("ver=").append(SystemUtil.getVersionName0(context));
		url.append("&");
		url.append("imei=").append(SystemUtil.getImei(context));
		client.get(url.toString(), responseHandler);

	}

	@Override
	public void getNewsList(Context context, String subId, String dir,
			String nid, String stamps, String cnt,
			ResponseHandlerInterface responseHandler) {

		AsyncHttpClient client = new AsyncHttpClient();

		StringBuffer url = new StringBuffer(NetConf.NEWS_LIST);
		url.append("ver=").append(SystemUtil.getVersionName0(context));
		url.append("&");
		url.append("subid=").append(subId);
		url.append("&");
		url.append("dir=").append(dir);
		url.append("&");
		url.append("nid=").append(nid);
		url.append("&");
		url.append("stamp=").append(stamps);
		url.append("&");
		url.append("cnt=").append(cnt);

		client.get(url.toString(), responseHandler);

	}

	@Override
	public void getNewsImage(Context context, String nid,
			ResponseHandlerInterface responseHandler) {

		AsyncHttpClient client = new AsyncHttpClient();

		StringBuffer url = new StringBuffer(NetConf.NEWS_IMAGE);
		url.append("ver=").append(SystemUtil.getVersionName0(context));
		url.append("&");
		url.append("nid=").append(nid);
		LogUtil.d("大图内容" + url);
		String u = "http://aqtyhj.blog.sohu.com/306671081.html?pvid=tc_blog&a=&b=http%3A%2F%2Fi0.itc.cn%2F20141114%2F3355_fcdb74de_b295_f437_e76f_94a17da1b198_1.jpg&defaut=pic";
		client.get(url.toString(), responseHandler);

	}

	@Override
	public void getCmtCommit(Context context, String nid, String token,
			String ctx, ResponseHandlerInterface responseHandler) {
		AsyncHttpClient client = new AsyncHttpClient();

		StringBuffer url = new StringBuffer(NetConf.CMT_COMMIT);
		url.append("ver=").append(SystemUtil.getVersionName0(context));
		url.append("&");
		url.append("nid=").append(nid);
		url.append("&");
		url.append("token=").append(token);
		url.append("&");
		url.append("imei=").append(SystemUtil.getImei(context));
		url.append("&");
		url.append("ctx=").append(ctx);

		client.get(url.toString(), responseHandler);

	}

}
