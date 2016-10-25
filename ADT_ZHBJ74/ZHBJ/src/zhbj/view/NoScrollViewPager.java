package zhbj.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 不允许滑动的viewPager
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

		return false;// 不拦截子控件的事件--------------重写父类的方法

	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		// 重写此方法，触摸时什么都不做，从而实现禁用
		return true;
	}

}
