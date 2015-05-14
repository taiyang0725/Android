package com.feicui.news.manager;

import android.content.Context;

import com.feicui.news.config.NetConf;
import com.feicui.news.infoInterface.CmtInfoListener;
import com.feicui.news.util.SystemUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.ResponseHandlerInterface;

public class CmtManager implements CmtInfoListener {

	@Override
	public void cmtList(Context context, String nid, String stamp, String cid,
			String dir, String cnt, ResponseHandlerInterface responseHandler) {

		AsyncHttpClient client = new AsyncHttpClient();

		StringBuffer url = new StringBuffer(NetConf.CMT_COMMIT);
		url.append("ver=").append(SystemUtil.getVersionName0(context));
		url.append("&");
		url.append("nid=").append(nid);
		url.append("&");
		url.append("type=").append("1");
		url.append("&");
		url.append("stamp=").append(stamp);
		url.append("&");
		url.append("cid=").append(cid);
		url.append("&");
		url.append("dir=").append(dir);
		url.append("&");
		url.append("cnt=").append(cnt);

		client.get(url.toString(), responseHandler);

	}

	@Override
	public void cmtNum(Context context, String nid,
			ResponseHandlerInterface responseHandler) {
		AsyncHttpClient client = new AsyncHttpClient();

		StringBuffer url = new StringBuffer(NetConf.CMT_COMMIT);
		url.append("ver=").append(SystemUtil.getVersionName0(context));
		url.append("&");
		url.append("nid=").append(nid);

		client.get(url.toString(), responseHandler);

	}

	@Override
	public void cmtCommit(Context context, String nid, String token,
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
