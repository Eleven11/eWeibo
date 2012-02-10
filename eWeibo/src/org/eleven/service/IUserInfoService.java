package org.eleven.service;

import java.util.List;

import org.eleven.modole.UserInfo;

import android.graphics.Bitmap;

public interface IUserInfoService {

	public abstract void close();

	// 获取users表中的UserID、Access Token、Access Secret的记录
	public List<UserInfo> listUsers(Boolean isSimple);

	// 判断users表中的是否包含某个UserID的记录
	public Boolean isHasUserInfo(String UserId);

	// 更新users表的记录，根据UserId更新用户昵称和用户图标
	public int updateUserInfo(String userName, Bitmap userIcon, String UserId);

	// 更新users表的记录
	public int updateUserInfo(UserInfo user);

	// 添加users表的记录
	public Long saveUserInfo(UserInfo user);

	// 删除users表的记录
	public int delUserInfo(String UserId);

	// 获取users表的记录
	public UserInfo getUserInfo(String UserId);

}
