package zhbj.base.imple.menu;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import zhbj.base.BaseMenuDetailPager;

/**
 * ²Ëµ¥ÏêÇéÒ³--»¥¶¯
 * 
 * @author Administrator
 * 
 */
public class InteractMenuDetailPager extends BaseMenuDetailPager {

	public InteractMenuDetailPager(Activity activity) {
		super(activity);

	}

	@Override
	public View initView() {
		TextView view = new TextView(mActivity);
		view.setText("²Ëµ¥ÏêÇéÒ³--»¥¶¯");
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);

		return view;

	}

}
