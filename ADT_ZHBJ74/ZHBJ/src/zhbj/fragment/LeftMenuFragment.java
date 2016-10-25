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
 * 侧边栏fragment
 * 
 * @author Administrator
 * 
 */
public class LeftMenuFragment extends BaseFragment {

	@ViewInject(R.id.lv)
	private ListView lv;

	private ArrayList<NewsMenuData> mNewsMenuData;// 侧边栏，网络数据对象

	private int mCurrentPos;// 当前被选中的item的位置

	private LeftMenuAdapter mAdpater;

	@Override
	public View initView() {
		View view = View.inflate(mactivity, R.layout.fragment_left_menu, null);
		// lv = (ListView) view.findViewById(R.id.lv);
		// 使用注入的方法 。。
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void initData() {

	}

	public void setMenuData(ArrayList<NewsMenuData> data) {
		mCurrentPos = 0;

		// 更新页面
		mNewsMenuData = data;
		mAdpater = new LeftMenuAdapter();
		lv.setAdapter(mAdpater);

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mCurrentPos = position;// 更新当前被选中的位置
				mAdpater.notifyDataSetChanged();// 刷新ListView

				// 收起侧边栏..
				toggle();

				// 侧边栏点击之后，要修改新闻中心的framlayout内容
				setCurrentDetailPager(position);
			}

		});
	}

	/**
	 * 设置当前菜单详情页
	 * 
	 * @param position
	 */
	protected void setCurrentDetailPager(int position) {

		// 获取新闻中心的对象
		MainActivity mainUi = (MainActivity) mactivity;

		// 获取contentFragment
		ContentFragment contentFragment = mainUi.getContentFragment();
		// 获取contentPager
		NewsCenterPager newscenterpager = contentFragment.getNewsCenterPager();

		// 获取新闻中心的framlayout的布局

		newscenterpager.setCurrentDetailPager(position);

	}

	/**
	 * 打开 或者关闭侧边栏
	 */
	protected void toggle() {
		MainActivity mainUI = (MainActivity) mactivity;
		SlidingMenu slidingMenu = mainUI.getSlidingMenu();
		slidingMenu.toggle();// 如果当前状态是开，调用后就关，反之亦然

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
				// 被选中
				tvMenu.setEnabled(true);// 文字变为红色
			} else {
				// 未选中
				tvMenu.setEnabled(false);// 文字变成白色
			}

			return view;
		}

	}
}
