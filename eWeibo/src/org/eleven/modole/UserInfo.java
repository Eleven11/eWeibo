package org.eleven.modole;

import android.graphics.drawable.Drawable;

public class UserInfo {

	public static final String ID = "ID";
	public static final String USERID = "USERID";
	public static final String TOKEN = "TOKEN";
	public static final String TOKENSECRET = "TOKENSECRET";
	public static final String USERNAME = "USERNAME";
	public static final String USERICON = "USERICON";

	private String id;
	private String userId;
	private String token;
	private String tokenSecret;
	private String userName;
	private Drawable userIcon;

	public UserInfo(String id, String userId, String token, String tokenSecret,
			String userName, Drawable userIcon) {
		super();
		this.id = id;
		this.userId = userId;
		this.token = token;
		this.tokenSecret = tokenSecret;
		this.userName = userName;
		this.userIcon = userIcon;
	}

	public UserInfo() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenSecret() {
		return tokenSecret;
	}

	public void setTokenSecret(String tokenSecret) {
		this.tokenSecret = tokenSecret;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Drawable getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(Drawable userIcon) {
		this.userIcon = userIcon;
	}

}
