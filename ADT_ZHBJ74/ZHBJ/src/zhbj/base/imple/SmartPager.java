package zhbj.base.imple;

import android.R;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import zhbj.base.BasePager;

/**
 * �ǻ۷���
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
		// Ҫ���겼��������
		TextView view = new TextView(mActivity);
		view.setText("�ǻ۷���");
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);

		// ������Ӹ��겼��
		flContent.addView(view);
		// �޸�ҳ�����
		tvTitle.setText("����");
		// ���ز˵���ť
		ib_menu.setVisibility(View.GONE);
	}
}
