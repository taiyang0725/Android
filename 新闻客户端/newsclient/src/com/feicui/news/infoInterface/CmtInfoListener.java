package com.feicui.news.infoInterface;

import android.content.Context;

import com.loopj.android.http.ResponseHandlerInterface;

/** ����ģ��ӿ� */
public interface CmtInfoListener {

	/**
	 * �������۵ķ���
	 * 
	 * @param context
	 *            �����Ķ���
	 * @param nid
	 *            ���ű��
	 * @param token
	 *            �û�����
	 * 
	 * 
	 * @param ctx
	 *            ��������
	 * @param responseHandler
	 *            ��Ӧ����ӿ�
	 */
	void cmtCommit(Context context, String nid, String token, String ctx,
			ResponseHandlerInterface responseHandler);

	/**
	 * 
	 * ��ʾ�����б�ķ���
	 * 
	 * @param context
	 *            �����Ķ���
	 * @param nid
	 *            ����Id
	 * 
	 * @param stamp
	 *            ʱ��
	 * @param cid
	 *            ���� Id
	 * @param dir
	 *            1���û��»���������ˢ�£����������ؽ��µ�����2�� �û�������������ˢ�£����������ؽ�������� ��
	 * 
	 * @param cnt
	 *            ��������Ŀ��
	 * @param responseHandler
	 *            ��Ӧ����ӿ�
	 */
	void cmtList(Context context, String nid, String stamp, String cid,
			String dir, String cnt, ResponseHandlerInterface responseHandler);

	/**
	 * ���������ķ���
	 * 
	 * @param context
	 *            �����Ķ���
	 * @param nid
	 *            ����Id
	 */
	void cmtNum(Context context, String nid,
			ResponseHandlerInterface responseHandler);

}
