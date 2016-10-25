package zhbj.fragment;

import java.util.ArrayList;

import zhbj.MainActivity;
import zhbj.base.BasePager;
import zhbj.base.imple.*;
import zhbj.view.NoScrollViewPager;

import com.example.zhbj.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 * ��fragment
 * 
 * @author Administrator
 * 
 */
public class ContentFragment extends BaseFragment {

	private NoScrollViewPager mViewPager;

	private ArrayList<BasePager> mPagers; // 5����ǩҳ�ļ���
	private RadioGroup rgGroup;

	@Override
	public View initView() {
		View view = View.inflate(mactivity, R.layout.fragment_content, null);
		mViewPager = (NoScrollViewPager) view.findViewById(R.id.vp_content);
		rgGroup = (RadioGroup) view.findViewById(R.id.rg_group);
		return view;

	}

	@Override
	public void initData() {
		mPagers = new ArrayList<BasePager>();
		// ���������ǩҳ
		mPagers.add(new HomePager(mactivity));
		mPagers.add(new NewsCenterPager(mactivity));
		mPagers.add(new SmartPager(mactivity));
		mPagers.add(new GovAffairsPager(mactivity));
		mPagers.add(new SettingPager(mactivity));

		mViewPager.setAdapter(new ContentAdapter());
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				BasePager pager = mPagers.get(position);
				// ֻ�е�ҳ��ѡ�е�ʱ�򣬲ż������ݣ���Լ��Դ����
				pager.initData();
				if (position == 0 || position == mPagers.size() - 1) {
					// ��ҳ������ҳ ���ò����
					setSlidingMenuEnable(false);
				} else {
					// ��������� ��
					setSlidingMenuEnable(true);

				}
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {

			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}

		});

		// ��һ�ξ͵���
		mPagers.get(0).initData();

		// �ֶ����ò����
		setSlidingMenuEnable(false);

		// ��radioGroup���õ���¼�
		rgGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb_home:
					mViewPager.setCurrentItem(0, false);// ��2��ʾ�Ƿ��л�������
					break;
				case R.id.rb_news:
					mViewPager.setCurrentItem(1, false);
					break;
				case R.id.rb_smart:
					mViewPager.setCurrentItem(2, false);
					break;
				case R.id.rb_gov:
					mViewPager.setCurrentItem(3, false);
					break;
				case R.id.rb_setting:
					mViewPager.setCurrentItem(4, false);
					break;

				default:
					break;
				}
			}
		});

	}

	/**
	 * �������߽��ò����
	 * 
	 * @param b
	 */
	public void setSlidingMenuEnable(boolean enable) {
		// ��ȡ�����
		MainActivity mainUi = (MainActivity) mactivity;
		SlidingMenu slidingMenu = mainUi.getSlidingMenu();

		if (enable) {
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		} else {
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		}
	}

	class ContentAdapter extends PagerAdapter {

		@Override
		public int getCount() {

			return mPagers.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {

			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			BasePager pager = mPagers.get(position);
			View view = pager.mRootView;// ��ȡ��ǰҳ�沼��

			// pager.initData();//��ʼ�����ݣ�viewpager��Ĭ�ϼ�����һ��ҳ��
			// Ϊ�˽�ʡ���������ܣ���Ҫ�ڴ˴����ó�ʼ�����ݵķ���
			container.addView(view);
			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {

			container.removeView((View) object);
		}
	}

	// ��ȡ�������ĵ�ҳ��
	public NewsCenterPager getNewsCenterPager() {
		NewsCenterPager pager = (NewsCenterPager) mPagers.get(1);
		return pager;
	}

}