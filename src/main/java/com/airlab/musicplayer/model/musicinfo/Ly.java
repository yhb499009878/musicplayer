package com.airlab.musicplayer.model.musicinfo;

/**
 * 留言
 */
import java.util.Date;

import com.airlab.musicplayer.model.BaseModel;
import com.airlab.musicplayer.model.IIdAware;

public class Ly extends BaseModel implements IIdAware {
	private static final long serialVersionUID = 6338038316306485726L;
	
	private Long id;// 主键id
	private String names;// 内容
	private String descs;// 内容
	private int kcid;//
	private Date times;// 时间

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescs() {
		return descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
	}

	public int getKcid() {
		return kcid;
	}

	public void setKcid(int kcid) {
		this.kcid = kcid;
	}

	public Date getTimes() {
		return times;
	}

	public void setTimes(Date times) {
		this.times = times;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

}
