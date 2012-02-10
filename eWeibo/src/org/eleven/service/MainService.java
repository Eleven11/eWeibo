package org.eleven.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eleven.modole.Task;

import weibo4android.Status;
import weibo4android.User;
import weibo4android.Weibo;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

public class MainService extends Service implements Runnable {
	public static Weibo weibo = new Weibo();
	public static User nowUser;
	public static List<Activity> activityList = new ArrayList<Activity>();// 保存所有Activity
	public static int lastActivityId;// 保存前端Activity编号
	// 保存用户头像
	public static HashMap<Integer, BitmapDrawable> alluserIcon = new HashMap<Integer, BitmapDrawable>();

	// 从集合中通过name获取Activity对象
	public static Activity getActivityByName(String name) {
		for (Activity ac : activityList) {
			if (ac.getClass().getName().indexOf(name) >= 0) {
				return ac;
			}
		}
		return null;
	}

	public static List<Task> taskList = new ArrayList<Task>();

	// 添加
	public static void newTask(Task task) {
		taskList.add(task);
	}

	public boolean isrun = true;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	// 更新UI
	private Handler hand = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Log.i("handleMessage", msg + "");
			Log.i("handleMessage.what", msg.what + "");
			switch (msg.what) {

			case Task.TASK_GET_USER_HOMETIMEINLINE:// 获取了微博首页信息
//				IWeiboActivity homectivity = (IWeiboActivity) MainService
//						.getActivityByName("Homectivity");
//				// 刷新类型 微博信息
//				homectivity.refresh(HomeActivity.REFRESH_WEIBO, msg.obj);
				break;
			case Task.TASK_USER_LOGIN:
//				IWeiboActivity loginActivity = (LoginActivity) MainService
//						.getActivityByName("LoginActivity");
//				// 登录
//				loginActivity.refresh(new Integer(LoginActivity.LOADING),
//						msg.obj);
				break;
			}
		}

	};

	// 执行任务
	public void doTask(Task task) {
		Log.i("doTask", task.getTaskId() + "");
		Message mess = hand.obtainMessage();
		mess.what = task.getTaskId();// 定义刷新UI的变化
		try {
			switch (task.getTaskId()) {
			case Task.TASK_GET_USER_HOMETIMEINLINE:// 获取首页微博\
				List<Status> allweibo = weibo.getFriendsTimeline();
				mess.obj = allweibo;
				break;
			case Task.TASK_USER_LOGIN:
				weibo.setToken((String) task.getTaskParam().get("token"),
						(String) task.getTaskParam().get("tokenSecret"));
				User u = weibo.showUser((String) task.getTaskParam().get(
						"userId"));
				MainService.nowUser = u;
				mess.obj = u;
				break;
			}
		} catch (Exception e) {
			mess.what = -100;
		}
		hand.sendMessage(mess);// 发送更新UI的消息给主线程
		taskList.remove(task);// 执行完任务
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		Log.i("MainService", "run()");
		while (isrun) {
			Task lastTask = null;
			synchronized (taskList) {
				if (taskList.size() > 0) {// 取任务
					lastTask = taskList.get(0);
					// 执行任务
					doTask(lastTask);
				}
			}
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		}
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		isrun = true;
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		isrun = false;
	}

	// alertUser 提示用户网络状态错误
	public static void AlertNetError(final Context con) {
		AlertDialog.Builder ab = new AlertDialog.Builder(con);
		ab.setTitle("网络错误");
		ab.setMessage("哎呦，网络出现问题了");
		ab.setNegativeButton("关闭", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
				exitApp(con);
			}

		});
		ab.setPositiveButton("设置网络", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				con.startActivity(new Intent(
						android.provider.Settings.ACTION_WIRELESS_SETTINGS));
			}
		});
		ab.create().show();
	}

	public static void exitApp(Context con) {
		for (Activity ac : activityList) {
			ac.finish();
		}
		//
		Intent it = new Intent("org.eleven.weibo.service.MainService");
		con.stopService(it);
	}
}