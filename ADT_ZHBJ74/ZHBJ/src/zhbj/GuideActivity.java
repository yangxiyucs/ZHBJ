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
	// ����ҳ���ͼƬ
	private int[] imageID = new int[] { R.drawable.guide_1, R.drawable.guide_2,
			R.drawable.guide_3 };
	private LinearLayout ll;

	// СԲ��֮��ľ���
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
		mViewPager.setAdapter(adapter);// ��������
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			// ĳ��ҳ�汻ѡ��
			@Override
			public void onPageSelected(int position) {
				if (position == imageViewsList.size() - 1) {
					bt.setVisibility(View.VISIBLE);
				} else {
					bt.setVisibility(View.INVISIBLE);
				}
			}

			// ��ҳ�滬�������еĻص�
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				// System.out.println("��ǰλ��" +position+";�ƶ�ƫ����"+positionOffset);

				// ����С������
				int leftMargin = (int) (mpointDis * positionOffset) + position
						* mpointDis;// ����С��㵱ǰ����߾�
				RelativeLayout.LayoutParams params = (LayoutParams) iv_red
						.getLayoutParams();
				params.leftMargin = leftMargin;

				// �������ò��ֲ���
				iv_red.setLayoutParams(params);
			}

			// ҳ��״̬�ĸı�
			@Override
			public void onPageScrollStateChanged(int state) {

			}

		});

		// ����layout�����������¼���λ��ȷ����֮���ٻ�ȡԲ�����
		iv_red.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						iv_red.getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);

						// --------(layout)----����ִ�н����Ļص�

						// ��������Բ��ľ���
						// �ƶ�����=�ڶ���Բ���left-��һ��Բ��Left
						// measures-layout->draw(��activity��Oncreate����ִ����֮��ſ�ʼ��)
						mpointDis = ll.getChildAt(1).getLeft()
								- ll.getChildAt(0).getLeft();

						// System.out.println("Բ�����" + mpointDis);

					}
				});
		bt.setOnClickListener(new View.OnClickListener() {

			// ������ҳ��
			@Override
			public void onClick(View v) {
				// ����SP���Ѿ����ǵ�һ�ν�����
				PrefUtils.setBoolean(getApplicationContext(), "is_first_enter",
						false);

				startActivity(new Intent(getApplicationContext(),
						MainActivity.class));
				finish();
			}
		});

	}

	// ��ʼ��Imageview����
	private void initData() {
		imageViewsList = new ArrayList<ImageView>();
		for (int i = 0; i < imageID.length; i++) {
			ImageView view = new ImageView(this);
			// ͨ�����ñ����������ÿ���Զ����ñ���
			view.setBackgroundResource(imageID[i]);
			imageViewsList.add(view);
			// Ҳ������,���ǿ�߲�����Ӧ��Ļview.setImageResource();

			// ��ʼ��СԲ��
			ImageView points = new ImageView(this);
			points.setImageResource(R.drawable.shape_points_gray);

			// ��ʼ�����ֲ�������߰������ݣ����ؼ���˭������˭��������
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);

			if (i > 0) {
				// �ӵڶ����㿪ʼ��������߾�
				params.leftMargin = 10;
			}
			points.setLayoutParams(params);
			ll.addView(points);
		}
	}

	/**
	 * viewpager �����������Խ�����ͼƬ����
	 * 
	 * @author Administrator
	 * 
	 */
	public class GuideAdapter extends PagerAdapter {

		// item�ĸ���
		@Override
		public int getCount() {

			return imageViewsList.size();
		}

		// ��ʼ��item����
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
