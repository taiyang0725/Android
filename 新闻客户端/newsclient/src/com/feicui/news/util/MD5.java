package com.feicui.news.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** MD5加密类 */
public class MD5 {
	/** 字符串MD5加密方法 */
	public static String getMD5Str(String path) {
		StringBuffer buffer = new StringBuffer();

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");

			byte[] bs = messageDigest.digest(path.getBytes());

			for (int i = 0; i < bs.length; i++) {
				String a = Integer.toHexString(bs[i] & 0xFF);
				if (a.length() == 1) {
					buffer.append("0");
				}
				buffer.append(a);
			}

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}

		return buffer.toString();

	}

	/**
	 * 文件MD5
	 * */
	public static String getMD5File(File file) {
		String value = null;
		FileInputStream in = null;

		try {
			in = new FileInputStream(file);
			MappedByteBuffer byteBuffer = in.getChannel().map(
					FileChannel.MapMode.READ_ONLY, 0, file.length());
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(byteBuffer);
			BigInteger bi = new BigInteger(1, md5.digest());
			value = bi.toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;

	}
}
