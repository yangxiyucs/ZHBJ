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
 * ��ҳ��
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
		// ������setcontentView ֮ǰ����
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
	 * ��ʼ�� fragment����
	 */

	private void initFragment() {
		FragmentManager fm = getSupportFragmentManager();
		// ��������
		FragmentTransaction transaction = fm.beginTransaction();

		// ��fragment�滻�겼�֣���1���겼��������ID,��2:Ҫ�滻���겼��
		transaction.replace(R.id.fl_leftmenu, new LeftMenuFragment(),
				TAG_LEFT_MENU);

		transaction.replace(R.id.fl_main, new ContentFragment(), TAG_CONTENT);

		transaction.commit();

		// ���ݱ���ҵ���Ӧ��fragment
		// Fragment fragment = fm.findFragmentByTag(CONTENT_TAG);

	}

	// ��ȡ�����fragment����
	public LeftMenuFragment getLeftmenuFragment() {
		FragmentManager fm = getSupportFragmentManager();
		LeftMenuFragment fragment = (LeftMenuFragment) fm
				.findFragmentByTag(TAG_LEFT_MENU);
		return fragment;

	}

	// ��ȡ��ҳcontent����
	public ContentFragment getContentFragment() {
		FragmentManager fm = getSupportFragmentManager();
		ContentFragment fragment = (ContentFragment) fm
				.findFragmentByTag(TAG_CONTENT);

		return fragment;

	}
}
