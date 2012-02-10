package org.eleven.activity;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.eleven.modole.UserInfo;
import org.eleven.service.IWeiboActivity;
import org.eleven.service.MainService;
import org.eleven.service.impl.UserInfoServiceImpl;
import org.eleven.utils.NetUtil;

import weibo4android.Status;
import weibo4android.User;
import weibo4android.Weibo;
import weibo4android.WeiboException;
import weibo4android.http.AccessToken;
import weibo4android.http.RequestToken;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class RegisterActivity extends Activity implements IWeiboActivity {

	private static final String TAG = "RegisterActivity";
	private static Context context;
	private RequestToken requestToken;
	private UserInfoServiceImpl userInfoService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.register);
		context = RegisterActivity.this.getApplicationContext();

		MainService.activityList.add(this);
		System.setProperty("weibo4j.oauth.consumerKey", Weibo.CONSUMER_KEY);
		System.setProperty("weibo4j.oauth.consumerSecret", Weibo.CONSUMER_SECRET);

		Weibo weibo = new Weibo();
		try {

			requestToken = weibo.getOAuthRequestToken("eweibo://RegisterActivity");
			Log.i(TAG, "requestToken:"+requestToken);
			
		} catch (WeiboException e) {
			// e.printStackTrace();
		}

		String url = requestToken.getAuthenticationURL() + "&display=mobile";

		Intent intent = new Intent(RegisterActivity.this, WebViewActivity.class);
		Bundle b = new Bundle();
		b.putString("url", url);
		intent.putExtras(b);
		startActivity(intent);

	}

	@Override
	protected void onNewIntent(Intent intent) {
		userInfoService = new UserInfoServiceImpl(this.getApplicationContext());
		Log.i(TAG, "onNewIntent intent :" + intent);
		// 在这里处理获取返回的oauth_verifier参数
		Uri uri = Uri.parse(intent.getStringExtra("url"));
		Log.i(TAG, "onNewIntent uri :" + uri);

		String verifier = uri.getQueryParameter(oauth.signpost.OAuth.OAUTH_VERIFIER);

		Log.i(TAG, "onNewIntent verifier :" + verifier);
		AccessToken accessToken = null;
		try {
			accessToken = requestToken.getAccessToken(verifier);
		} catch (WeiboException e) {
			e.printStackTrace();
		}

		Log.i(TAG, "user id: " + accessToken.getUserId());

		Weibo weibo = new Weibo();
		weibo.setToken(accessToken.getToken(), accessToken.getTokenSecret());

		List<Status> homeTimeline = null;

		try {
			homeTimeline = weibo.getHomeTimeline();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Status s : homeTimeline) {

			Log.i(TAG, s.getUser().getName() + "---" + s.getText());

		}
		UserInfo user = null;
		String userId = accessToken.getUserId() + "";
		User u = null;
		URL url;
		BufferedInputStream in = null;
		try {
			u = weibo.showUser(userId);

			try {
				url = new URL(u.getProfileImageURL().toString());
				in = new BufferedInputStream(url.openStream());
				// 保存的图片文件名
				in.close();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (userInfoService.isHasUserInfo(userId)) {

			Log.e("UserInfo", "userId" + userId);
			user = userInfoService.getUserInfo(userId);
			Log.i("UserInfo", "userName" + user.getUserName());
			user = new UserInfo(userId, userId, accessToken.getToken(), accessToken.getTokenSecret(), u.getName(),
					Drawable.createFromStream(in, "image"));
		} else {

			user = new UserInfo(userId, userId, accessToken.getToken(), accessToken.getTokenSecret(), u.getName(),
					Drawable.createFromStream(in, "image"));
			Log.e("UserInfo", "add");
			long id = userInfoService.saveUserInfo(user);
			if (id > 0) {

			} else {

			}
		}

		super.onNewIntent(intent);
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