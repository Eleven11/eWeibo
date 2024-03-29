package org.eleven.utils;

/**
 * 获取配置文件
 * @author bywyu
 *
 */
public  class ConfigUtil {
	
	private static ConfigUtil instance;
	
	//Activity之间传递值时的KEY
	public static final String OAUTH_VERIFIER_URL = "oauth_verifier_url";
	
	private String curWeibo = "";
	private String appKey = "";
	private String appSecret = "";
	private String request_token_url = "";
	private String authoriz_token_url = "";
	private String access_token_url = ""; 
	
	public static final String SINAW = "sina";
	public static final String QQW = "qq";
	public static final String SOHUW = "sohu";
	public static final String WANGYIW = "wangyi";
	
	public static String callBackUrl = "eweibo://LogoActivity";
	
	//--------------------qq
	private final String qq_AppKey = "801089760 ";
	private final String qq_AppSecret = "a3fb8c42a9b4400c384d0c956d05a4a6 ";
	private final String qq_Request_token_url = "https://open.t.qq.com/cgi-bin/request_token";
	private final String qq_Authoriz_token_url = "https://open.t.qq.com/cgi-bin/authorize";
	private final String qq_Access_token_url = "https://open.t.qq.com/cgi-bin/access_token";
	
	//---------------------sina
	private final String sina_AppKey = "4232444770";
	private final String sina_AppSecret = "7e0ffa6f0992c756cab9eaaf06b51bb7";
	private final String sina_Request_token_url = "http://api.t.sina.com.cn/oauth/request_token";
	private final String sina_Authoriz_token_url = "http://api.t.sina.com.cn/oauth/authorize";
	private final String sina_Access_token_url = "http://api.t.sina.com.cn/oauth/access_token";
	
	//--------------------sohu
	//API Key YJWf6bU9PXiH9X0Y3IKM
	private final String sohu_AppKey = "YJWf6bU9PXiH9X0Y3IKM";
	private final String sohu_AppSecret = "E4B-0icTStH49HiCfQg-qJAB#1DXlq=Ltge)xeZY";
	private final String sohu_Request_token_url = "http://api.t.sohu.com/oauth/request_token";
	private final String sohu_Authoriz_token_url = "http://api.t.sohu.com/oauth/authorize";
	private final String sohu_Access_token_url = "http://api.t.sohu.com/oauth/access_token";
	
	//---------------------wangyi
	private final String wangyi_AppKey = "F0wXLCY83wgWDE9N";
	private final String wangyi_AppSecret = "EF1ciwd9tZPqAiDtMyUrIIjS5uyDNeEZ";
	private final String wangyi_Request_token_url = "http://api.t.163.com/oauth/request_token";
	private final String wangyi_Authoriz_token_url = "http://api.t.163.com/oauth/authenticate";
	private final String wangyi_Access_token_url = "http://api.t.163.com/oauth/access_token";
	
    public static synchronized ConfigUtil getInstance() {
        if (instance == null) {
            instance = new ConfigUtil();
        }
        return instance;
    }
	
	public ConfigUtil(){
		
	}
	
	/**
	 * 初始化QQ认证信息
	 */
	public void initQqData() {
		setAppKey(qq_AppKey);
		setAppSecret(qq_AppSecret);
		setRequest_token_url(qq_Request_token_url);
		setAuthoriz_token_url(qq_Authoriz_token_url);
		setAccess_token_url(qq_Access_token_url);
    }
	
	/**
	 * 初始化SINA认证信息
	 */
	public void initSinaData() {
		setAppKey(sina_AppKey);
		setAppSecret(sina_AppSecret);
		setRequest_token_url(sina_Request_token_url);
		setAuthoriz_token_url(sina_Authoriz_token_url);
		setAccess_token_url(sina_Access_token_url);
    }
	
	/**
	 * 初始化SOHU认证信息
	 */
	public void initSohuData() {
		setAppKey(sohu_AppKey);
		setAppSecret(sohu_AppSecret);
		setRequest_token_url(sohu_Request_token_url);
		setAuthoriz_token_url(sohu_Authoriz_token_url);
		setAccess_token_url(sohu_Access_token_url);
    }
	
	/**
	 * 初始化网易认证信息
	 */
	public void initWangyiData(){
		setAppKey(wangyi_AppKey);
		setAppSecret(wangyi_AppSecret);
		setRequest_token_url(wangyi_Request_token_url);
		setAuthoriz_token_url(wangyi_Authoriz_token_url);
		setAccess_token_url(wangyi_Access_token_url);
	}
	
	public String getCurWeibo() {
    	return curWeibo;
    }
	
	/**
	 * 设置当前操作的weibo
	 * 		不同的weibo请求存在着差异
	 * @param curWeibo
	 */
	public void setCurWeibo(String curWeibo) {
    	this.curWeibo = curWeibo;
    }
	
	public String getAppKey() {
    	return appKey;
    }

	public void setAppKey(String appKey) {
    	this.appKey = appKey;
    }

	public String getAppSecret() {
    	return appSecret;
    }

	public void setAppSecret(String appSecret) {
    	this.appSecret = appSecret;
    }

	public String getRequest_token_url() {
    	return request_token_url;
    }

	public void setRequest_token_url(String requestTokenUrl) {
    	request_token_url = requestTokenUrl;
    }
	
	public String getAuthoriz_token_url() {
    	return authoriz_token_url;
    }

	public void setAuthoriz_token_url(String authorizTokenUrl) {
    	authoriz_token_url = authorizTokenUrl;
    }

	public String getAccess_token_url() {
    	return access_token_url;
    }

	public void setAccess_token_url(String accessTokenUrl) {
    	access_token_url = accessTokenUrl;
    }
}
