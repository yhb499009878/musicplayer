package com.airlab.musicplayer.model.musicinfo;

import com.airlab.musicplayer.model.BaseModel;
import com.airlab.musicplayer.model.IIdAware;

/**
 * 音乐类
 *
 */
public class Kc extends BaseModel implements IIdAware {
	private static final long serialVersionUID = 1759166869953634083L;
	
	private Long id;// 主键id
	private String names;// 名称
	private int tid;// 名称
	private String tnames;// 名称
	private String ishy;//
	private String zjr;// 人
	private String xx;// 信息
	private String descs;// 描述
	private int bfcs;// 播放次数
	private int plcs;// 评论次数
	private int sccs;// 顶次数
	private String url1;// --
	private String url2;// --

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getZjr() {
		return zjr;
	}

	public void setZjr(String zjr) {
		this.zjr = zjr;
	}

	public String getXx() {
		return xx;
	}

	public void setXx(String xx) {
		this.xx = xx;
	}

	public String getDescs() {
		return descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
	}

	public int getBfcs() {
		return bfcs;
	}

	public void setBfcs(int bfcs) {
		this.bfcs = bfcs;
	}

	public int getPlcs() {
		return plcs;
	}

	public void setPlcs(int plcs) {
		this.plcs = plcs;
	}

	public int getSccs() {
		return sccs;
	}

	public void setSccs(int sccs) {
		this.sccs = sccs;
	}

	public String getUrl1() {
		return url1;
	}

	public void setUrl1(String url1) {
		this.url1 = url1;
	}

	public String getUrl2() {
		return url2;
	}

	public void setUrl2(String url2) {
		this.url2 = url2;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getTnames() {
		return tnames;
	}

	public void setTnames(String tnames) {
		this.tnames = tnames;
	}

	public String getIshy() {
		return ishy;
	}

	public void setIshy(String ishy) {
		this.ishy = ishy;
	}

}
