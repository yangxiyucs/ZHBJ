package zhbj;

import zhbj.base.imple.NewsCenterPager;
import zhbj.fragment.ContentFragment;
import zhbj.fragment.LeftMenuFragment;

import com.example.zhbj.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

/**
 * 主页面
 * 
 * @author Administrator
 * 
 */
public class MainActivity extends SlidingFragmentActivity {
	private static final String TAG_CONTENT = "CONTENT";
	private static final String TAG_LEFT_MENU = "LEFT_MENU";

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 必须在setcontentView 之前调用
		setContentView(R.layout.activity_main);

		// Utils.doSomething();
		// R.drawable.p10;

		setBehindContentView(R.layout.left_menu);
		SlidingMenu slidingMenu = getSlidingMenu();
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		slidingMenu.setBehindOffset(200);

		initFragment();

	}

	/**
	 * 初始化 fragment对象
	 */

	private void initFragment() {
		FragmentManager fm = getSupportFragmentManager();
		// 开启事务
		FragmentTransaction transaction = fm.beginTransaction();

		// 用fragment替换贞布局；参1：贞布局容器的ID,参2:要替换的贞布局
		transaction.replace(R.id.fl_leftmenu, new LeftMenuFragment(),
				TAG_LEFT_MENU);

		transaction.replace(R.id.fl_main, new ContentFragment(), TAG_CONTENT);

		transaction.commit();

		// 根据标记找到对应的fragment
		// Fragment fragment = fm.findFragmentByTag(CONTENT_TAG);

	}

	// 获取侧边栏fragment对象
	public LeftMenuFragment getLeftmenuFragment() {
		FragmentManager fm = getSupportFragmentManager();
		LeftMenuFragment fragment = (LeftMenuFragment) fm
				.findFragmentByTag(TAG_LEFT_MENU);
		return fragment;

	}

	// 获取主页content对象
	public ContentFragment getContentFragment() {
		FragmentManager fm = getSupportFragmentManager();
		ContentFragment fragment = (ContentFragment) fm
				.findFragmentByTag(TAG_CONTENT);

		return fragment;

	}
}
