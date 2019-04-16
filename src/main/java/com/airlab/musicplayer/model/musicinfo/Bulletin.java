package com.airlab.musicplayer.model.musicinfo;

import java.util.Date;

import com.airlab.musicplayer.model.BaseModel;
import com.airlab.musicplayer.model.IIdAware;

/**
 * 公告
 *
 */
public class Bulletin extends BaseModel implements IIdAware {
	private static final long serialVersionUID = 7577090420388266127L;

	private Long id;

	private String content;
	private Date bulletinTime = new Date();

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getBulletinTime() {
		return bulletinTime;
	}

	public void setBulletinTime(Date bulletinTime) {
		this.bulletinTime = bulletinTime;
	}

}
