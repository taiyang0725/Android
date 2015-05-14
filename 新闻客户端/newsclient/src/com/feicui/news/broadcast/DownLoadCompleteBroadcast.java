package com.feicui.news.broadcast;

import java.io.File;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.widget.Toast;

import com.feicui.news.R;
import com.feicui.news.util.LogUtil;
import com.feicui.news.util.MD5;

/**
 * 版本升级下载完成的广播
 * */
public class DownLoadCompleteBroadcast extends BroadcastReceiver {
	private String md5;

	private long id;

	public DownLoadCompleteBroadcast(String md5, long id) {
		super();
		this.md5 = md5;
		this.id = id;
	}

	@Override
	public void onReceive(Context context, Intent intent) {

		DownloadManager.Query query = new DownloadManager.Query();

		query.setFilterById(id);

		query.setFilterByStatus(DownloadManager.STATUS_SUCCESSFUL);

		DownloadManager manager = (DownloadManager) context
				.getSystemService(Context.DOWNLOAD_SERVICE);

		Cursor cursor = manager.query(query);

		if (cursor.moveToFirst()) {

			String path = cursor.getString(cursor
					.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
			String fileMd5 = MD5.getMD5File(new File(path.substring(8)));
			if (md5.equals(fileMd5)) {
				LogUtil.d("File" + fileMd5);
				LogUtil.d("md5" + md5);

				Intent intent2 = new Intent(Intent.ACTION_VIEW);

				intent2.setDataAndType(Uri.parse(path),
						"application/vnd.android.package-archive");

				intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

				context.startActivity(intent2);
			} else {
				Toast.makeText(context, R.string.download_error,
						Toast.LENGTH_SHORT).show();
			}
			/** 注销广播 */
			context.unregisterReceiver(this);

		}

	}

}
