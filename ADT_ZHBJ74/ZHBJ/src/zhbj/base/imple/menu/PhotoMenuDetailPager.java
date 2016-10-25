package zhbj.base.imple.menu;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import zhbj.base.BaseMenuDetailPager;
/**
 * 菜单详情页--组图
 * @author Administrator
 *
 */
public class PhotoMenuDetailPager extends BaseMenuDetailPager {

	public PhotoMenuDetailPager(Activity activity) {
		super(activity);
		
	}

	@Override
	public View initView() {
		TextView view = new TextView(mActivity);
		view.setText("菜单详情页--组图");
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);

		return view;
	}

}
