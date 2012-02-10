package org.eleven.activity;

import org.eleven.service.IWeiboActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

public class MoreActivity extends Activity implements IWeiboActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TextView tv = new TextView(this);
		tv.setText("This is MoreActivity!");
		tv.setGravity(Gravity.CENTER);
		setContentView(tv);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub

	}

}