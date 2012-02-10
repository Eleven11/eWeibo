package org.eleven.modole;

import java.util.Map;

public class Task {
	private int taskId;// 任务编号
	private Map<String, Object> taskParam;// 任务参数
	public static final int TASK_GET_USER_INFO = 0;// 获取用户详细信息
	public static final int TASK_GET_USER_HOMETIMEINLINE = 1;// 获取用户首页博客
	public static final int TASK_GET_USER_IMAGE_ICON = 2;// 获取用户头象图片
	public static final int TASK_GET_USER_FRIEND = 3;// 获取用户所有好友
	public static final int TASK_GET_USER_HOMETIMEINLINE_MORE = 4;// 获取用户首页博客下一页
	public static final int TASK_NEW_WEIBO = 5;// 发表新微博
	public static final int TASK_USER_LOGIN = 6;// 用户登录

	public Task(int taskId, Map<String, Object> taskParam) {
		super();
		this.taskId = taskId;
		this.taskParam = taskParam;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public Map<String, Object> getTaskParam() {
		return taskParam;
	}

	public void setTaskParam(Map<String, Object> taskParam) {
		this.taskParam = taskParam;
	}

}
