package com.airlab.musicplayer.model.musicinfo;

import com.airlab.musicplayer.model.BaseModel;
import com.airlab.musicplayer.model.IIdAware;
import com.airlab.musicplayer.model.ResourceInfo;

public class SongList extends BaseModel implements IIdAware {
	private static final long serialVersionUID = -8649949984882856215L;

	private long id;
	private Long userId; // 歌单创建者
	private String songListName; // 歌单名称
	private String songListDesc; // 歌单描述
	private String coverResourceId; // 封面资源

	private ResourceInfo coverResourceInfo; // 封面资源信息，显示用

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getSongListName() {
		return songListName;
	}

	public void setSongListName(String songListName) {
		this.songListName = songListName;
	}

	public String getSongListDesc() {
		return songListDesc;
	}

	public void setSongListDesc(String songListDesc) {
		this.songListDesc = songListDesc;
	}

	public String getCoverResourceId() {
		return coverResourceId;
	}

	public void setCoverResourceId(String coverResourceId) {
		this.coverResourceId = coverResourceId;
	}

	public ResourceInfo getCoverResourceInfo() {
		return coverResourceInfo;
	}

	public void setCoverResourceInfo(ResourceInfo coverResourceInfo) {
		this.coverResourceInfo = coverResourceInfo;
	}

}
