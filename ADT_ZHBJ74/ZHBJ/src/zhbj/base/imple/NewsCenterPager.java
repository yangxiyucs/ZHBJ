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
 * 新闻
 * 
 * @author Administrator
 * 
 */
public class NewsCenterPager extends BasePager {
	private ArrayList<BaseMenuDetailPager> mMenuDetailPagers;// 菜单详情页 集合

	private NewsMenu mNewsData;// 分类信息网络数据

	public NewsCenterPager(Activity activity) {
		super(activity);

	}

	@Override
	public void initData() {
		// 要给贞布局填充对象
		// TextView view = new TextView(mActivity);
		// view.setText("新闻");
		// view.setTextColor(Color.RED);
		// view.setTextSize(22);
		// view.setGravity(Gravity.CENTER);
		//
		// // 布局添加给贞布局
		// flContent.addView(view);
		// // 修改页面标题
		// tvTitle.setText("新闻");

		// 显示菜单按钮
		ib_menu.setVisibility(View.VISIBLE);

		// 先判断有没有缓存，如果有的话，就加载缓存
		String cache = CacheUtils.getCache(GlobalConstant.CATEGORY_URL,mActivity);
		if (!TextUtils.isEmpty(cache)) {
			// 已经有缓存了，加载缓存
			System.out.println("发现缓存了。。。");
			processData(cache);
		} else {
			// 请求服务器，获取数据
			// 开源框架：XUtils
			getDataFromServer();
		}

		// 请求服务器，获取数据
		// 开源框架：XUtils
		getDataFromServer();
	}

	/**
	 * 从服务器获取数据 需要2个权限，网络和写外部存储CFCFCFFF
	 */
	private void getDataFromServer() {
		HttpUtils utils = new HttpUtils();
		//
		utils.send(HttpMethod.GET, GlobalConstant.CATEGORY_URL,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// 请求成功
						String result = responseInfo.result;// 获取服务器返回结果
						System.out.println("服务器返回结果" + result);

						// 获取数据
						processData(result);
						// 写缓存
						CacheUtils.setCache(GlobalConstant.CATEGORY_URL, result,
								mActivity);
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// 请求失败

						error.printStackTrace();
						Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT);
					}

				});
	}

	/**
	 * JSON数据解析
	 * 
	 */
	private void processData(String json) {
		// Gson:google的json
		Gson gson = new Gson();
		mNewsData = gson.fromJson(json, NewsMenu.class);
		System.out.println("解析结果" + mNewsData);

		// 获取侧边栏对象
		MainActivity mainUI = (MainActivity) mActivity;
		LeftMenuFragment fragment = mainUI.getLeftmenuFragment();

		// 给侧边栏设置数据
		fragment.setMenuData(mNewsData.data);

		// 初始化4个菜单详情叶
		mMenuDetailPagers = new ArrayList<BaseMenuDetailPager>();

		mMenuDetailPagers.add(new NewsMenuDetailPager(mActivity, mNewsData.data
				.get(0).children));
		mMenuDetailPagers.add(new TopicMenuDetailPager(mActivity));

		mMenuDetailPagers.add(new PhotoMenuDetailPager(mActivity));
		mMenuDetailPagers.add(new InteractMenuDetailPager(mActivity));

		// 将新闻菜单详情页设置为默认页面(第一次加载的页面时，页面跟新闻一致)
		setCurrentDetailPager(0);

	}

	// 设置菜单详情页
	public void setCurrentDetailPager(int position) {
		// 重新给fragmentLayout添加内容
		BaseMenuDetailPager pager = mMenuDetailPagers.get(position);
		View view = pager.mRootView;

		// 因为每一次加载会重叠，所以每次先清除一下
		flContent.removeAllViews();

		flContent.addView(view);

		// 初始化页面数据
		pager.initData();

		// 更新标题
		String title = mNewsData.data.get(position).title;
		tvTitle.setText(title);
	}

}
