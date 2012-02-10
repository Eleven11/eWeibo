package org.eleven.activity;

import org.eleven.utils.JavaScriptInterface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends Activity {

	private static final String TAG = "WebViewActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.oauth_webview);
		Log.i(TAG, "onCreate");
		WebView _webView = (WebView) findViewById(R.id.eweibo_webview);
		Intent i = this.getIntent();
		if (!i.equals(null)) {
			Bundle b = i.getExtras();
			if (b != null) {
				if (b.containsKey("url")) {
					String url = b.getString("url");

					// 这行很重要一点要有，不然网页的认证按钮会无效
					_webView.getSettings().setJavaScriptEnabled(true);
					_webView.getSettings().setSupportZoom(true);
					// _webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
					_webView.getSettings().setBuiltInZoomControls(true);
					_webView.loadUrl(url);
					_webView.requestFocus();
				}
			}
		}

		_webView.addJavascriptInterface(new JavaScriptInterface(), "Methods");
		WebViewClient wvc = new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {

				Log.i(TAG, "onPageFinished :" + url);
				view.loadUrl("javascript:window.Methods.getHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");

				if (url.startsWith("eweibo")) {

					 Intent intent = new Intent(WebViewActivity.this, MainActivity.class);
					 intent.putExtra("url", url);
					 startActivity(intent);
				}

				super.onPageFinished(view, url);
			}
		};
		_webView.setWebViewClient(wvc);

	}

}