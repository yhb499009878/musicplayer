package com.airlab.musicplayer.model.musicinfo;

import com.airlab.musicplayer.model.BaseModel;
import com.airlab.musicplayer.model.IIdAware;

public class SongListMapping extends BaseModel implements IIdAware {
	private static final long serialVersionUID = 5043382447795089344L;

	private Long id;

	private Long songId;
	private Long songListId;

	public SongListMapping() {
		super();
	}

	public SongListMapping(Long songId, Long songListId) {
		super();
		this.songId = songId;
		this.songListId = songListId;
	}

	public Long getSongId() {
		return songId;
	}

	public void setSongId(Long songId) {
		this.songId = songId;
	}

	public Long getSongListId() {
		return songListId;
	}

	public void setSongListId(Long songListId) {
		this.songListId = songListId;
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
