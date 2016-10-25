package zhbj;

import com.example.zhbj.R;

import zhbj.utils.PrefUtils;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class SplashActivity extends Activity {

	private RelativeLayout rl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		rl = (RelativeLayout) findViewById(R.id.rl_root);

		// ��ת����
		RotateAnimation animRotate = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);

		animRotate.setDuration(1000);// ����ʱ��
		animRotate.setFillAfter(true);

		// ���Ŷ���
		ScaleAnimation animScale = new ScaleAnimation(0, 1, 0, 1,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);

		animScale.setDuration(1000);
		animScale.setFillAfter(true);

		// ���䶯��
		AlphaAnimation animAlpha = new AlphaAnimation(0, 1);
		animAlpha.setDuration(2000);
		animAlpha.setFillAfter(true);

		// ��������
		AnimationSet animationSet = new AnimationSet(true);
		animationSet.addAnimation(animRotate);
		animationSet.addAnimation(animScale);
		animationSet.addAnimation(animAlpha);

		// ��������
		rl.startAnimation(animationSet);
		animationSet.setAnimationListener(new AnimationListener() {

			private Intent intent;

			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// ����������תҳ��
				// ����ǵ�һ�ν��룬����������
				// ��������ҳ��

				boolean isFirstEnter = PrefUtils.getBoolean(
						SplashActivity.this, "is_first_enter", true);

				if (isFirstEnter) {
					intent = new Intent(SplashActivity.this,
							GuideActivity.class);

				} else {
					intent = new Intent(SplashActivity.this, MainActivity.class);

				}
				startActivity(intent);
				finish();
			}
		});

	}

}
