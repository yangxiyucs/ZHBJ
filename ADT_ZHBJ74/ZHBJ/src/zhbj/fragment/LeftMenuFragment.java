package zhbj.fragment;

import java.util.ArrayList;

import zhbj.MainActivity;
import zhbj.base.imple.NewsCenterPager;
import zhbj.domain.NewsMenu.NewsMenuData;

import com.example.zhbj.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * �����fragment
 * 
 * @author Administrator
 * 
 */
public class LeftMenuFragment extends BaseFragment {

	@ViewInject(R.id.lv)
	private ListView lv;

	private ArrayList<NewsMenuData> mNewsMenuData;// ��������������ݶ���

	private int mCurrentPos;// ��ǰ��ѡ�е�item��λ��

	private LeftMenuAdapter mAdpater;

	@Override
	public View initView() {
		View view = View.inflate(mactivity, R.layout.fragment_left_menu, null);
		// lv = (ListView) view.findViewById(R.id.lv);
		// ʹ��ע��ķ��� ����
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void initData() {

	}

	public void setMenuData(ArrayList<NewsMenuData> data) {
		mCurrentPos = 0;

		// ����ҳ��
		mNewsMenuData = data;
		mAdpater = new LeftMenuAdapter();
		lv.setAdapter(mAdpater);

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mCurrentPos = position;// ���µ�ǰ��ѡ�е�λ��
				mAdpater.notifyDataSetChanged();// ˢ��ListView

				// ��������..
				toggle();

				// ��������֮��Ҫ�޸��������ĵ�framlayout����
				setCurrentDetailPager(position);
			}

		});
	}

	/**
	 * ���õ�ǰ�˵�����ҳ
	 * 
	 * @param position
	 */
	protected void setCurrentDetailPager(int position) {

		// ��ȡ�������ĵĶ���
		MainActivity mainUi = (MainActivity) mactivity;

		// ��ȡcontentFragment
		ContentFragment contentFragment = mainUi.getContentFragment();
		// ��ȡcontentPager
		NewsCenterPager newscenterpager = contentFragment.getNewsCenterPager();

		// ��ȡ�������ĵ�framlayout�Ĳ���

		newscenterpager.setCurrentDetailPager(position);

	}

	/**
	 * �� ���߹رղ����
	 */
	protected void toggle() {
		MainActivity mainUI = (MainActivity) mactivity;
		SlidingMenu slidingMenu = mainUI.getSlidingMenu();
		slidingMenu.toggle();// �����ǰ״̬�ǿ������ú�͹أ���֮��Ȼ

	}

	class LeftMenuAdapter extends BaseAdapter {

		@Override
		public int getCount() {

			return mNewsMenuData.size();
		}

		@Override
		public Object getItem(int position) {

			return mNewsMenuData.get(position);
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(mactivity, R.layout.list_item_left_menu,
					null);

			TextView tvMenu = (TextView) view.findViewById(R.id.tv_menu);
			NewsMenuData item = (NewsMenuData) getItem(position);
			tvMenu.setText(item.title);

			if (position == mCurrentPos) {
				// ��ѡ��
				tvMenu.setEnabled(true);// ���ֱ�Ϊ��ɫ
			} else {
				// δѡ��
				tvMenu.setEnabled(false);// ���ֱ�ɰ�ɫ
			}

			return view;
		}

	}
}
