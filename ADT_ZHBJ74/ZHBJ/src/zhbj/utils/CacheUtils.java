package zhbj.utils;

import android.content.Context;

/**
 * 网络缓存的工具类
 * 
 * @author Administrator
 * 
 */
public class CacheUtils {
	/**
	 * 设置缓存 以url为key,以json为value 保存到本地
	 * 
	 * @param url
	 * @param value
	 */
	public static void setCache(String url, String value, Context context) {
		// 也可以用文件缓存：以MD5(url)为文件名，以Jason为文件内容
		PrefUtils.setString(context, url, value);
	}

	/**
	 * 获取缓存
	 * 
	 * @param url
	 * @param value
	 * @param context
	 * @return
	 */
	public static String getCache(String url, Context context) {
		// 文件缓存： 查找有没有一个文件叫url,有的话，说明有缓存
		return PrefUtils.getString(context, url, null);
	}
}
