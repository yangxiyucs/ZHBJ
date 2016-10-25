package zhbj.base.imple.menu;

import java.util.ArrayList;

import com.example.zhbj.R;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.viewpagerindicator.TabPageIndicator;

import zhbj.MainActivity;
import zhbj.base.BaseMenuDetailPager;
import zhbj.domain.NewsMenu.NewsTabData;
import android.app.Activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;

import android.view.ViewGroup;

/**
 * 菜单详情页--新闻
 * 
 * ViewPagerIndicator使用流程： 1:引入库 2.解决support-v4冲突（让两个版本一致） 3.从例子程序中拷贝布局文件
 * 4.从例子程序中拷贝相关代码（指示器和viewpager绑定；重写getPagerTitle返回标题）
 * 
 * 5.在清单文件中增加样式
 * 
 * 6.背景修改为白色 7.修改样式——背景样式&文字样式
 * 
 * @author Administrator
 * 
 */

public class NewsMenuDetailPager extends BaseMenuDetailPager implements
		OnPageChangeListener {

	// 用注解来声明组件，等同于findviewbyId
	@ViewInject(R.id.vp_news_menu_detail)
	private ViewPager mViewPager;
	@ViewInject(R.id.indicator)
	private TabPageIndicator mIndicator;
	private ArrayList<NewsTabData> mTabData;
	private ArrayList<TabDetailPager> mTabDetailPagers;

	public NewsMenuDetailPager(Activity activity,
			ArrayList<NewsTabData> children) {
		super(activity);
		mTabData = children;
	}

	@Override
	public View initView() {

		View view = View.inflate(mActivity, R.layout.paper_news_menu_detail,
				null);
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void initData() {
		mTabDetailPagers = new ArrayList<TabDetailPager>();
		// 初始化页签
		for (int i = 0; i < mTabData.size(); i++) {
			TabDetailPager pager = new TabDetailPager(mActivity,mTabData.get(i));
			mTabDetailPagers.add(pager);
		}

		mViewPager.setAdapter(new NewsMenuDetailAdapter());
		// 将ViewPager和指示器绑定在一起:必须在viewpager设置完数据之后再绑定
		mIndicator.setViewPager(mViewPager);

		// mViewPager.setOnPageChangeListener(this);
		mIndicator.setOnPageChangeListener(this);// 此处必须设置给Indicator
													// 指示器，不能给ViewPager
	}

	class NewsMenuDetailAdapter extends PagerAdapter {

		@Override
		public CharSequence getPageTitle(int position) {
			// 指定 指示器的标题
			String title = mTabData.get(position).title;

			return title;
		}

		@Override
		public int getCount() {

			return mTabDetailPagers.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {

			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			TabDetailPager pager = mTabDetailPagers.get(position);
			View view = pager.mRootView;
			container.addView(view);
			// 在此，对pager的数据初始化。并且返回布局文件
			pager.initData();
			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);

		}

	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {
		System.out.println("当前位置" + position);
		if (position == 0) {
			// 开启侧边栏
			setSlidingMenuEnable(true);
		} else {
			// 禁用侧边栏
			setSlidingMenuEnable(false);

		}
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}

	/**
	 * 开启或者禁用侧边栏
	 * 
	 * @param b
	 */

	public void setSlidingMenuEnable(boolean enable) {
		// 获取侧边栏
		MainActivity mainUi = (MainActivity) mActivity;
		SlidingMenu slidingMenu = mainUi.getSlidingMenu();

		if (enable) {
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		} else {
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		}
	}

	@OnClick(R.id.bt_next)
	public void nextPage(View v) {
		// 跳到下一个页面
		int currentItem = mViewPager.getCurrentItem();
		currentItem++;
		mViewPager.setCurrentItem(currentItem);

	}
}
