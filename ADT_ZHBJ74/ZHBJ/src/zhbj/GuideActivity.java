package zhbj;

import java.util.ArrayList;

import com.example.zhbj.R;
import zhbj.utils.PrefUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class GuideActivity extends Activity {
	private ViewPager mViewPager;
	private ArrayList<ImageView> imageViewsList;
	// 引导页面的图片
	private int[] imageID = new int[] { R.drawable.guide_1, R.drawable.guide_2,
			R.drawable.guide_3 };
	private LinearLayout ll;

	// 小圆点之间的距离
	private int mpointDis;
	private ImageView iv_red;
	private Button bt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_guide);

		mViewPager = (ViewPager) findViewById(R.id.vp_guide);
		ll = (LinearLayout) findViewById(R.id.ll_container);
		iv_red = (ImageView) findViewById(R.id.iv_red);
		bt = (Button) findViewById(R.id.bt);
		initData();

		GuideAdapter adapter = new GuideAdapter();
		mViewPager.setAdapter(adapter);// 设置数据
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			// 某个页面被选中
			@Override
			public void onPageSelected(int position) {
				if (position == imageViewsList.size() - 1) {
					bt.setVisibility(View.VISIBLE);
				} else {
					bt.setVisibility(View.INVISIBLE);
				}
			}

			// 当页面滑动过程中的回调
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				// System.out.println("当前位置" +position+";移动偏移量"+positionOffset);

				// 更新小红点距离
				int leftMargin = (int) (mpointDis * positionOffset) + position
						* mpointDis;// 计算小红点当前的左边距
				RelativeLayout.LayoutParams params = (LayoutParams) iv_red
						.getLayoutParams();
				params.leftMargin = leftMargin;

				// 重新设置布局参数
				iv_red.setLayoutParams(params);
			}

			// 页面状态的改变
			@Override
			public void onPageScrollStateChanged(int state) {

			}

		});

		// 监听layout方法结束的事件，位置确定好之后再获取圆点距离
		iv_red.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						iv_red.getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);

						// --------(layout)----方法执行结束的回调

						// 计算两个圆点的距离
						// 移动距离=第二个圆点的left-第一个圆点Left
						// measures-layout->draw(当activity的Oncreate方法执行完之后才开始走)
						mpointDis = ll.getChildAt(1).getLeft()
								- ll.getChildAt(0).getLeft();

						// System.out.println("圆点距离" + mpointDis);

					}
				});
		bt.setOnClickListener(new View.OnClickListener() {

			// 跳到主页面
			@Override
			public void onClick(View v) {
				// 更新SP，已经不是第一次进入了
				PrefUtils.setBoolean(getApplicationContext(), "is_first_enter",
						false);

				startActivity(new Intent(getApplicationContext(),
						MainActivity.class));
				finish();
			}
		});

	}

	// 初始化Imageview对象
	private void initData() {
		imageViewsList = new ArrayList<ImageView>();
		for (int i = 0; i < imageID.length; i++) {
			ImageView view = new ImageView(this);
			// 通过设置背景，可以让宽高自动设置背景
			view.setBackgroundResource(imageID[i]);
			imageViewsList.add(view);
			// 也可以用,但是宽高不能适应屏幕view.setImageResource();

			// 初始化小圆点
			ImageView points = new ImageView(this);
			points.setImageResource(R.drawable.shape_points_gray);

			// 初始化布局参数，宽高包裹内容，父控件是谁，就是谁声明参数
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);

			if (i > 0) {
				// 从第二个点开始，设置左边距
				params.leftMargin = 10;
			}
			points.setLayoutParams(params);
			ll.addView(points);
		}
	}

	/**
	 * viewpager 适配器，可以将多张图片放入
	 * 
	 * @author Administrator
	 * 
	 */
	public class GuideAdapter extends PagerAdapter {

		// item的个数
		@Override
		public int getCount() {

			return imageViewsList.size();
		}

		// 初始化item布局
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView imageView = imageViewsList.get(position);
			container.addView(imageView);

			return imageView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {

			container.removeView((View) object);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {

			return arg0 == arg1;
		}

	}

}
