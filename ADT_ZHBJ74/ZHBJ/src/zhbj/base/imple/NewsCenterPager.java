package zhbj.base.imple;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;

import android.text.TextUtils;

import android.view.View;

import android.widget.Toast;
import zhbj.MainActivity;
import zhbj.base.BaseMenuDetailPager;
import zhbj.base.BasePager;
import zhbj.base.imple.menu.InteractMenuDetailPager;
import zhbj.base.imple.menu.NewsMenuDetailPager;
import zhbj.base.imple.menu.PhotoMenuDetailPager;
import zhbj.base.imple.menu.TopicMenuDetailPager;
import zhbj.domain.NewsMenu;
import zhbj.fragment.LeftMenuFragment;
import zhbj.global.GlobalConstant;
import zhbj.utils.CacheUtils;

/**
 * ����
 * 
 * @author Administrator
 * 
 */
public class NewsCenterPager extends BasePager {
	private ArrayList<BaseMenuDetailPager> mMenuDetailPagers;// �˵�����ҳ ����

	private NewsMenu mNewsData;// ������Ϣ��������

	public NewsCenterPager(Activity activity) {
		super(activity);

	}

	@Override
	public void initData() {
		// Ҫ���겼��������
		// TextView view = new TextView(mActivity);
		// view.setText("����");
		// view.setTextColor(Color.RED);
		// view.setTextSize(22);
		// view.setGravity(Gravity.CENTER);
		//
		// // ������Ӹ��겼��
		// flContent.addView(view);
		// // �޸�ҳ�����
		// tvTitle.setText("����");

		// ��ʾ�˵���ť
		ib_menu.setVisibility(View.VISIBLE);

		// ���ж���û�л��棬����еĻ����ͼ��ػ���
		String cache = CacheUtils.getCache(GlobalConstant.CATEGORY_URL,mActivity);
		if (!TextUtils.isEmpty(cache)) {
			// �Ѿ��л����ˣ����ػ���
			System.out.println("���ֻ����ˡ�����");
			processData(cache);
		} else {
			// �������������ȡ����
			// ��Դ��ܣ�XUtils
			getDataFromServer();
		}

		// �������������ȡ����
		// ��Դ��ܣ�XUtils
		getDataFromServer();
	}

	/**
	 * �ӷ�������ȡ���� ��Ҫ2��Ȩ�ޣ������д�ⲿ�洢CFCFCFFF
	 */
	private void getDataFromServer() {
		HttpUtils utils = new HttpUtils();
		//
		utils.send(HttpMethod.GET, GlobalConstant.CATEGORY_URL,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// ����ɹ�
						String result = responseInfo.result;// ��ȡ���������ؽ��
						System.out.println("���������ؽ��" + result);

						// ��ȡ����
						processData(result);
						// д����
						CacheUtils.setCache(GlobalConstant.CATEGORY_URL, result,
								mActivity);
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// ����ʧ��

						error.printStackTrace();
						Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT);
					}

				});
	}

	/**
	 * JSON���ݽ���
	 * 
	 */
	private void processData(String json) {
		// Gson:google��json
		Gson gson = new Gson();
		mNewsData = gson.fromJson(json, NewsMenu.class);
		System.out.println("�������" + mNewsData);

		// ��ȡ���������
		MainActivity mainUI = (MainActivity) mActivity;
		LeftMenuFragment fragment = mainUI.getLeftmenuFragment();

		// ���������������
		fragment.setMenuData(mNewsData.data);

		// ��ʼ��4���˵�����Ҷ
		mMenuDetailPagers = new ArrayList<BaseMenuDetailPager>();

		mMenuDetailPagers.add(new NewsMenuDetailPager(mActivity, mNewsData.data
				.get(0).children));
		mMenuDetailPagers.add(new TopicMenuDetailPager(mActivity));

		mMenuDetailPagers.add(new PhotoMenuDetailPager(mActivity));
		mMenuDetailPagers.add(new InteractMenuDetailPager(mActivity));

		// �����Ų˵�����ҳ����ΪĬ��ҳ��(��һ�μ��ص�ҳ��ʱ��ҳ�������һ��)
		setCurrentDetailPager(0);

	}

	// ���ò˵�����ҳ
	public void setCurrentDetailPager(int position) {
		// ���¸�fragmentLayout�������
		BaseMenuDetailPager pager = mMenuDetailPagers.get(position);
		View view = pager.mRootView;

		// ��Ϊÿһ�μ��ػ��ص�������ÿ�������һ��
		flContent.removeAllViews();

		flContent.addView(view);

		// ��ʼ��ҳ������
		pager.initData();

		// ���±���
		String title = mNewsData.data.get(position).title;
		tvTitle.setText(title);
	}

}
