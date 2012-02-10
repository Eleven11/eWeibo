package org.eleven.service.impl;

import java.util.List;

import org.eleven.dao.DataHelper;
import org.eleven.modole.UserInfo;
import org.eleven.service.IUserInfoService;

import android.content.Context;
import android.graphics.Bitmap;

public class UserInfoServiceImpl implements IUserInfoService {

	private DataHelper helper;

	public DataHelper getHelper() {
		return helper;
	}

	public void setHelper(DataHelper helper) {
		this.helper = helper;
	}

	public UserInfoServiceImpl(Context context) {
		helper = new DataHelper(context);
	}

	@Override
	public void close() {
		helper.close();

	}

	@Override
	public List<UserInfo> listUsers(Boolean isSimple) {
		return helper.listUsers(isSimple);
	}

	@Override
	public Boolean isHasUserInfo(String userId) {
		return helper.isHasUserInfo(userId);
	}

	@Override
	public int updateUserInfo(String userName, Bitmap userIcon, String userId) {
		return helper.updateUserInfo(userName, userIcon, userId);
	}

	@Override
	public int updateUserInfo(UserInfo user) {
		return helper.updateUserInfo(user);
	}

	@Override
	public Long saveUserInfo(UserInfo user) {
		return helper.saveUserInfo(user);
	}

	@Override
	public int delUserInfo(String userId) {
		return helper.delUserInfo(userId);
	}

	@Override
	public UserInfo getUserInfo(String userId) {
		return helper.getUserInfo(userId);
	}

}
