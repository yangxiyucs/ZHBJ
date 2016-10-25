package zhbj.base.imple;

import android.R;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import zhbj.base.BasePager;

/**
 * 智慧服务
 * 
 * @author Administrator
 * 
 */
public class SmartPager extends BasePager {

	public SmartPager(Activity activity) {
		super(activity);

	}

	@Override
	public void initData() {
		// 要给贞布局填充对象
		TextView view = new TextView(mActivity);
		view.setText("智慧服务");
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);

		// 布局添加给贞布局
		flContent.addView(view);
		// 修改页面标题
		tvTitle.setText("生活");
		// 隐藏菜单按钮
		ib_menu.setVisibility(View.GONE);
	}
}
