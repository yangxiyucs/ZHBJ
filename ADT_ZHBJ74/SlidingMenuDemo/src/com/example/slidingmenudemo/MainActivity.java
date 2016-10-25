package com.example.slidingmenudemo;


import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;


import android.os.Bundle;


/**
 * 使用slidingMenu 1.引用SlidingMenu库 2.继承SlidingActivity
 * -----------注意：引用library必须和代码在同一文件夹
 * 
 * 
 * @author Administrator
 * 
 */
public class MainActivity extends SlidingFragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//设置侧边栏 
		setBehindContentView(R.layout.left_menu);
		
		SlidingMenu slidingMenu = getSlidingMenu();
		
		//设置右边栏
		slidingMenu.setSecondaryMenu(R.layout.right_menu);
		slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
		
		//设置全屏出没
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
	
	//设置侧边栏宽度
		slidingMenu.setBehindOffset(200);
	}

}
