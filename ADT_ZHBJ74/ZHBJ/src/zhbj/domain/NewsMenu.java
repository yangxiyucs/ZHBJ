package zhbj.domain;

import java.util.ArrayList;

/**
 * ������Ϣ��װ�� ʹ��Gson����ʱ��������д���� 1.��{}�������󣬷�[]��������(arraylist)
 * 2.�����ֶε����ƣ�Ҫ��gson�ֶη��ص�����Ҫ�߶�һ�� 3.
 * 
 * @author Administrator
 * 
 */

public class NewsMenu {
	public int retcode;
	public ArrayList<Integer> extend;

	public ArrayList<NewsMenuData> data;

	// ������˵�����
	public class NewsMenuData {
		public int id;
		public String title;
		public int type;

		public ArrayList<NewsTabData> children;

		@Override
		public String toString() {
			return "NewsMenuData [title=" + title + ", children=" + children
					+ "]";
		}

	}

	// ҳǩ�Ķ���
	public class NewsTabData {
		public int id;
		public String title;
		public int type;
		public String url;

		@Override
		public String toString() {
			return "NewsTabData [title=" + title + "]";
		}

	}

	@Override
	public String toString() {
		return "NewsMenu [data=" + data + "]";
	}

}
