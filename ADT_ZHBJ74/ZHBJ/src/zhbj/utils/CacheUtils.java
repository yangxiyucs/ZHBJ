package zhbj.utils;

import android.content.Context;

/**
 * ���绺��Ĺ�����
 * 
 * @author Administrator
 * 
 */
public class CacheUtils {
	/**
	 * ���û��� ��urlΪkey,��jsonΪvalue ���浽����
	 * 
	 * @param url
	 * @param value
	 */
	public static void setCache(String url, String value, Context context) {
		// Ҳ�������ļ����棺��MD5(url)Ϊ�ļ�������JasonΪ�ļ�����
		PrefUtils.setString(context, url, value);
	}

	/**
	 * ��ȡ����
	 * 
	 * @param url
	 * @param value
	 * @param context
	 * @return
	 */
	public static String getCache(String url, Context context) {
		// �ļ����棺 ������û��һ���ļ���url,�еĻ���˵���л���
		return PrefUtils.getString(context, url, null);
	}
}
