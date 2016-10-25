package zhbj.view;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * ͷ�������Զ���viewPager
 * 
 * @author Administrator
 * 
 */
public class TopNewsViewPager extends ViewPager {

	private int startX;
	private int startY;

	public TopNewsViewPager(Context context) {
		super(context);

	}

	public TopNewsViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	/**
	 * 1.���»��������� 2.���һ������ҵ�ǰ�ǵ�һ��ҳ�棬��Ҫ���� 3.���󻬶����ҵ�ǰ�����һ��ҳ��,��Ҫ����
	 * 
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {

		getParent().requestDisallowInterceptTouchEvent(true);
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startX = (int) ev.getX();
			startY = (int) ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			int endX = (int) ev.getX();
			int endY = (int) ev.getY();

			int dx = endX - startX;
			int dy = endY - startY;
			break;

		default:
			break;
		}

		return super.dispatchTouchEvent(ev);
	}

}