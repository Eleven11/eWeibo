package org.eleven.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eleven.modole.UserInfo;
import org.eleven.service.IWeiboActivity;
import org.eleven.service.MainService;
import org.eleven.service.impl.UserInfoServiceImpl;
import org.eleven.utils.NetUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;

public class LoginActivity extends Activity implements IWeiboActivity {

	private static final String TAG = "LoginActivity";
	private static Context context;
	
	private static boolean flag = false;

	private PopupWindow popupWindow;
	private List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
	private Map<String, Object> map = new HashMap<String, Object>();
	private ListView listView;
	private ListAdapter listAdapter;
	private UserInfoServiceImpl userInfoService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.login);
		context = LoginActivity.this.getApplicationContext();
		userInfoService = new UserInfoServiceImpl(context);


		
		ImageButton button = (ImageButton)findViewById(R.id.switchuserButton);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				popAwindow(v);
				
			}
		});
		
		
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				
//			}
//		}).start();
		
		
		MainService.activityList.add(this);
	}

	private void popAwindow(View parent) {
		LayoutInflater lay = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = lay.inflate(R.layout.pop_user_login, null);

		// 初始化listview，加载数据。
		listView = (ListView) v.findViewById(R.id.user_list);
		mapList = new ArrayList<Map<String, Object>>();
		
		
		initUsers();
		
		
		
		
		listAdapter = new SimpleAdapter(v.getContext(), mapList,
				 R.layout.login_users, new String[] { "name", "userId",
				 "userIcon" }, new int[] { R.id.name, R.id.userId,
				 R.id.image });
		
		listView.setAdapter(listAdapter);
		listView.setItemsCanFocus(false);
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				
			}
		});

		popupWindow = new PopupWindow(v, 500, 260);

		popupWindow.setFocusable(true);
		popupWindow.update();
		popupWindow.showAtLocation(parent, Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
		
	}
	
	
	
	
	

	private void initUsers() {
		List<UserInfo> uil = userInfoService.listUsers(true);
		
		for(UserInfo ui:uil){
			map = new HashMap<String, Object>();
//			map.put("name", "孙东君");
//			map.put("userId", "xxxx");
			
			map.put("name", ui.getUserName());
			map.put("userId", "xxxx");
			mapList.add(map);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i(TAG, "onResume");
		init();
	}

	@Override
	public void init() {
		if (NetUtil.checkNet(context)) {
			// Intent it = new Intent("org.eleven.weibo.service.MainService");
			// this.startService(it);
		} else {
			MainService.AlertNetError(this);
		}
	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
	}
}