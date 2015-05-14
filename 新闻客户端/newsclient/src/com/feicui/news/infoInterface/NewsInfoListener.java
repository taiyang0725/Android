package com.feicui.news.infoInterface;

import android.content.Context;

import com.loopj.android.http.ResponseHandlerInterface;

/**
 * 2.1 新闻模块
 * */
public interface NewsInfoListener {

	void getNewsSort(Context context, ResponseHandlerInterface responseHandler);

	/**
	 * 新闻列表的方法
	 * 
	 * @param context
	 *            上下文对象
	 * @param responseHandler
	 *            相应处理接口
	 * @param subId
	 *            分类名
	 * @param dir
	 *            方向 dir=1 1:服务器需要返回最新的新闻列表2：表示用户上拉界面主动刷新，服务器需返回用户需要的之前的数据
	 * @param nid
	 *            新闻id
	 * @param stamps
	 *            新闻时间
	 * @param cnt
	 *            新闻数目
	 * 
	 */
	void getNewsList(Context context, String subId, String dir, String nid,
			String stamps, String cnt, ResponseHandlerInterface responseHandler);

	/**
	 * 新闻内容的方法
	 * 
	 * @param context
	 *            上下文对象
	 * @param responseHandler
	 *            相应处理接口
	 * @param nid
	 *            新闻编号
	 */
	void getNewsImage(Context context, String nid,
			ResponseHandlerInterface responseHandler);

	/**
	 * 评论
	 * 
	 * @param context
	 *            上下文对象
	 * @param responseHandler
	 *            相应处理接口
	 * @param nid
	 *            新闻编号
	 * @param token
	 *            手机标示符
	 * @param ctx
	 *            评论内容
	 */
	void getCmtCommit(Context context, String nid, String token, String ctx,
			ResponseHandlerInterface responseHandler);
}
