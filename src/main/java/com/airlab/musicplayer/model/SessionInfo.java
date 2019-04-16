package com.airlab.musicplayer.model;

public class SessionInfo {
	private String sessionId; // 会话ID
	private long userId; // 用户ID
	private String username; // 用户名
	private String isvip; // 是否是VIP
	private String email; // 邮箱
	private String sex; // 性别
	private String tel; // 手机号
	private String realname; // 真实姓名
	private long updateTime;// 刷新时间

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIsvip() {
		return isvip;
	}

	public void setIsvip(String isvip) {
		this.isvip = isvip;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
