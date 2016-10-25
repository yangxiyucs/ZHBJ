package zhbj.base.imple;

import android.R;
import android.app.Activity;
import android.graphics.Color;
import android.os.IBinder;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import zhbj.base.BasePager;

/**
 * ��ҳ
 * 
 * @author Administrator
 * 
 */
public class HomePager extends BasePager {

	public HomePager(Activity activity) {
		super(activity);

	}

	@Override
	public void initData() {
		// Ҫ���겼��������
		TextView view = new TextView(mActivity);
		view.setText("��ҳ");
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);

		// ������Ӹ��겼��
		flContent.addView(view);

		// �޸�ҳ�����
		tvTitle.setText("�ǻ۱���");

		// ���ز˵���ť
		ib_menu.setVisibility(View.VISIBLE);
	}

}
