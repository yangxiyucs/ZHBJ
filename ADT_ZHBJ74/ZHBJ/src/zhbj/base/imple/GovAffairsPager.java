package zhbj.base.imple;

import android.R;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import zhbj.base.BasePager;

/**
 * ����
 * 
 * @author Administrator
 * 
 */
public class GovAffairsPager extends BasePager {

	public GovAffairsPager(Activity activity) {
		super(activity);

	}

	@Override
	public void initData() {
		// Ҫ���겼��������
		TextView view = new TextView(mActivity);
		view.setText("����");
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);

		// ������Ӹ��겼��
		flContent.addView(view);
		// �޸�ҳ�����
		tvTitle.setText("����");
		// ���ذ�ť
		ib_menu.setVisibility(View.VISIBLE);
	}
}
