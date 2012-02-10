package org.eleven.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetUtil {
	/**
	 * 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
	 * 
	 * @param context
	 * @return
	 */
	public static boolean checkNet(Context context) {
		try {
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			// Log.i("net", info.getState() + "");
			if (connectivity != null) {
				// 获取网络连接管理的对象
				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (info != null && info.isAvailable()) {
					// 判断当前网络是否已经连接
					// Log.i("net", info.getState() + "");
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		} catch (Exception e) {
		}
		return false;
	}
}