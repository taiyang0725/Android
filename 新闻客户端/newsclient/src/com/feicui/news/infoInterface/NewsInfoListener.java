package com.feicui.news.infoInterface;

import android.content.Context;

import com.loopj.android.http.ResponseHandlerInterface;

/**
 * 2.1 ����ģ��
 * */
public interface NewsInfoListener {

	void getNewsSort(Context context, ResponseHandlerInterface responseHandler);

	/**
	 * �����б�ķ���
	 * 
	 * @param context
	 *            �����Ķ���
	 * @param responseHandler
	 *            ��Ӧ����ӿ�
	 * @param subId
	 *            ������
	 * @param dir
	 *            ���� dir=1 1:��������Ҫ�������µ������б�2����ʾ�û�������������ˢ�£��������践���û���Ҫ��֮ǰ������
	 * @param nid
	 *            ����id
	 * @param stamps
	 *            ����ʱ��
	 * @param cnt
	 *            ������Ŀ
	 * 
	 */
	void getNewsList(Context context, String subId, String dir, String nid,
			String stamps, String cnt, ResponseHandlerInterface responseHandler);

	/**
	 * �������ݵķ���
	 * 
	 * @param context
	 *            �����Ķ���
	 * @param responseHandler
	 *            ��Ӧ����ӿ�
	 * @param nid
	 *            ���ű��
	 */
	void getNewsImage(Context context, String nid,
			ResponseHandlerInterface responseHandler);

	/**
	 * ����
	 * 
	 * @param context
	 *            �����Ķ���
	 * @param responseHandler
	 *            ��Ӧ����ӿ�
	 * @param nid
	 *            ���ű��
	 * @param token
	 *            �ֻ���ʾ��
	 * @param ctx
	 *            ��������
	 */
	void getCmtCommit(Context context, String nid, String token, String ctx,
			ResponseHandlerInterface responseHandler);
}
