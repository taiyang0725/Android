package com.feicui.news.infoInterface;

import android.content.Context;

import com.loopj.android.http.ResponseHandlerInterface;

/** 评论模块接口 */
public interface CmtInfoListener {

	/**
	 * 发布评论的方法
	 * 
	 * @param context
	 *            上下文对象
	 * @param nid
	 *            新闻编号
	 * @param token
	 *            用户令牌
	 * 
	 * 
	 * @param ctx
	 *            评论内容
	 * @param responseHandler
	 *            相应处理接口
	 */
	void cmtCommit(Context context, String nid, String token, String ctx,
			ResponseHandlerInterface responseHandler);

	/**
	 * 
	 * 显示评论列表的方法
	 * 
	 * @param context
	 *            上下文对象
	 * @param nid
	 *            新闻Id
	 * 
	 * @param stamp
	 *            时间
	 * @param cid
	 *            评论 Id
	 * @param dir
	 *            1：用户下滑界面主动刷新，服务器返回较新的评论2： 用户上拉界面主动刷新，服务器返回较早的评论 ；
	 * 
	 * @param cnt
	 *            最大更新条目数
	 * @param responseHandler
	 *            相应处理接口
	 */
	void cmtList(Context context, String nid, String stamp, String cid,
			String dir, String cnt, ResponseHandlerInterface responseHandler);

	/**
	 * 评论数量的方法
	 * 
	 * @param context
	 *            上下文对象
	 * @param nid
	 *            新闻Id
	 */
	void cmtNum(Context context, String nid,
			ResponseHandlerInterface responseHandler);

}
