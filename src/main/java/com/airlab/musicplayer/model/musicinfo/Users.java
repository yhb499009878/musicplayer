package com.airlab.musicplayer.model.musicinfo;

import com.airlab.musicplayer.model.BaseModel;
import com.airlab.musicplayer.model.IIdAware;

public class Users extends BaseModel implements IIdAware {
	private static final long serialVersionUID = 6047657086440020220L;

	private Long id;// 主键id
	private String username;// 名称
	private String password;// 人
	private String tel;// 信息
	private String sex;// 描述
	private String email;// --
	private String isvip;// --

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

}
