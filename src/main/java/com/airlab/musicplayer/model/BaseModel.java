package com.airlab.musicplayer.model;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseModel implements Serializable{
	private static final long serialVersionUID = -8140245439399094402L;
	private Date updateTime = new Date();
	
	private Date createTime = new Date();
	
	private int status = 0;

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
