package org.eleven.activity;

import java.util.List;

import org.eleven.modole.UserInfo;
import org.eleven.service.IWeiboActivity;
import org.eleven.service.MainService;
import org.eleven.service.impl.UserInfoServiceImpl;
import org.eleven.utils.NetUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.Toast;

public class EWeiboActivity extends Activity implements IWeiboActivity {
	private Intent intent;
	private static final String TAG = "EWeiboActivity";
	private static Context context;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.start);
		context = EWeiboActivity.this.getApplicationContext();

		AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
		animation.setDuration(1000);

		ImageView img_logo = (ImageView) this.findViewById(R.id.logo);
		img_logo.setAnimation(animation);

		animation.setAnimationListener(new AnimationListener() {

			public void onAnimationEnd(Animation animation) {

				Log.i(TAG, "onAnimationEnd");

				
				UserInfoServiceImpl userInfoService = new UserInfoServiceImpl(context);

				List<UserInfo> ul = userInfoService.listUsers(new Boolean(false));
				Log.i(TAG, "userInfoList size:"+ul.size());
				
				userInfoService.close();
				if (ul.size() > 0) {
					// Toast.makeText(context, "木有用户",
					// Toast.LENGTH_SHORT).show();
					intent = new Intent(EWeiboActivity.this, LoginActivity.class);
					startActivity(intent);
				} else {
					// Toast.makeText(context, "已存储用户",
					// Toast.LENGTH_SHORT).show();
					intent = new Intent(EWeiboActivity.this, RegisterActivity.class);
					startActivity(intent);
				}
			}

			public void onAnimationRepeat(Animation animation) {

			}

			public void onAnimationStart(Animation animation) {

			}
		});
		MainService.activityList.add(this);
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
			Intent it = new Intent("org.eleven.weibo.service.MainService");
			this.startService(it);
		} else {
			MainService.AlertNetError(this);
		}
	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub

	}

}