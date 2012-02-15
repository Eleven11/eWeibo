package org.eleven.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.TabHost;

public class MainActivity extends TabActivity implements OnCheckedChangeListener {

	private static final String HOME = "Home";
	private static final String MORE = "More";
	private static final String MENU = "menu";
	private static final String MSG = "msg";
	private static final String SEARCH = "search";
	private static final String SELFINFO = "selfinfo";

	private TabHost mTabHost;

	private Intent homeActivity;
	private Intent moreActivity;
	private Intent menuActivity;
	private Intent msgActivity;
	private Intent searchActivity;
	private Intent selfinfoActivity;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.maintabs);

		this.moreActivity = new Intent(this, MoreActivity.class);
		this.homeActivity = new Intent(this, HomeActivity.class);
		this.msgActivity = new Intent(this, MsgActivity.class);
		this.searchActivity = new Intent(this, SearchActivity.class);
		this.menuActivity = new Intent(this, MenuActivity.class);
		this.selfinfoActivity = new Intent(this, SelfInfoActivity.class);
		((RadioButton) findViewById(R.id.radio_home)).setOnCheckedChangeListener(this);
//		((RadioButton) findViewById(R.id.radio_more)).setOnCheckedChangeListener(this);
		((RadioButton) findViewById(R.id.radio_msg)).setOnCheckedChangeListener(this);
		((RadioButton) findViewById(R.id.radio_menu)).setOnCheckedChangeListener(this);
//		((RadioButton) findViewById(R.id.radio_search)).setOnCheckedChangeListener(this);
		((RadioButton) findViewById(R.id.radio_selfinfo)).setOnCheckedChangeListener(this);

		initIntent();
	}

	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			switch (buttonView.getId()) {
			case R.id.radio_home:
				this.mTabHost.setCurrentTabByTag(HOME);
				break;
			case R.id.radio_more:
				this.mTabHost.setCurrentTabByTag(MORE);
				break;
			case R.id.radio_msg:
				this.mTabHost.setCurrentTabByTag(MSG);
				break;
			case R.id.radio_search:
				this.mTabHost.setCurrentTabByTag(SEARCH);
				break;
			case R.id.radio_menu:
				this.mTabHost.setCurrentTabByTag(MENU);
				break;
			case R.id.radio_selfinfo:
				this.mTabHost.setCurrentTabByTag(SELFINFO);
				break;

			}
		}
	}

	private void initIntent() {
		this.mTabHost = getTabHost();
		TabHost localTabHost = this.mTabHost;
		localTabHost.addTab(buildTabSpec(HOME, R.string.main_home, R.drawable.icon_home, this.homeActivity));
		localTabHost.addTab(buildTabSpec(MSG, R.string.main_msg, R.drawable.icon_meassage, this.msgActivity));
		localTabHost.addTab(buildTabSpec(MORE, R.string.more, R.drawable.icon_more, this.moreActivity));
		localTabHost.addTab(buildTabSpec(MENU, R.string.main_menu, R.drawable.icon_square, this.menuActivity));
		localTabHost.addTab(buildTabSpec(SEARCH, R.string.main_search, R.drawable.icon_more, this.searchActivity));
		localTabHost.addTab(buildTabSpec(SELFINFO, R.string.main_selfinfo, R.drawable.icon_selfinfo, this.selfinfoActivity));

	}

	private TabHost.TabSpec buildTabSpec(String tag, int resLabel, int resIcon, final Intent content) {
		return this.mTabHost.newTabSpec(tag).setIndicator(getString(resLabel), getResources().getDrawable(resIcon))
				.setContent(content);
	}
}