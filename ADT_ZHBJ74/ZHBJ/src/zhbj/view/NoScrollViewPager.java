package zhbj.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * ����������viewPager
 * 
 * @author Administrator
 * 
 */
public class NoScrollViewPager extends ViewPager {

	public NoScrollViewPager(Context context) {
		super(context);

	}

	public NoScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {

		return false;// �������ӿؼ����¼�--------------��д����ķ���

	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		// ��д�˷���������ʱʲô���������Ӷ�ʵ�ֽ���
		return true;
	}

}
