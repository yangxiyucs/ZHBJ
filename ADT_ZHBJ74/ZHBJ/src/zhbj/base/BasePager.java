package zhbj.base;

import zhbj.MainActivity;

import com.example.zhbj.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * 5����ǩҳ�Ļ���
 * 
 * @author Administrator
 * 
 */
public class BasePager {
	public Activity mActivity;
	public TextView tvTitle;
	public FrameLayout flContent;
	public ImageButton ib_menu;
	public View mRootView;// ��ǰҳ��Ĳ��ֶ���

	public BasePager(Activity activity) {
		mActivity = activity;

		mRootView = initView();
	}

	// ��ʼ������
	public View initView() {
		View view = View.inflate(mActivity, R.layout.basepager, null);

		ib_menu = (ImageButton) view.findViewById(R.id.ib_menu);
		flContent = (FrameLayout) view.findViewById(R.id.fl_content);
		tvTitle = (TextView) view.findViewById(R.id.tv_title);

		ib_menu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				toggle();
			}
		});

		return view;

	}

	/**
	 * �� ���߹رղ����
	 */
	protected void toggle() {
		MainActivity mainUI = (MainActivity) mActivity;
		SlidingMenu slidingMenu = mainUI.getSlidingMenu();
		slidingMenu.toggle();// �����ǰ״̬�ǿ������ú�͹أ���֮��Ȼ

	}

	// ��ʼ������
	public void initData() {

	}
}
