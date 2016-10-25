package com.example.slidingmenudemo;


import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;


import android.os.Bundle;


/**
 * ʹ��slidingMenu 1.����SlidingMenu�� 2.�̳�SlidingActivity
 * -----------ע�⣺����library����ʹ�����ͬһ�ļ���
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
		
		//���ò���� 
		setBehindContentView(R.layout.left_menu);
		
		SlidingMenu slidingMenu = getSlidingMenu();
		
		//�����ұ���
		slidingMenu.setSecondaryMenu(R.layout.right_menu);
		slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
		
		//����ȫ����û
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
	
	//���ò�������
		slidingMenu.setBehindOffset(200);
	}

}
