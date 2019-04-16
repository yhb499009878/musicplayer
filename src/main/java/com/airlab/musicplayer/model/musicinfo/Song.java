package com.airlab.musicplayer.model.musicinfo;

import com.airlab.musicplayer.model.BaseModel;
import com.airlab.musicplayer.model.IIdAware;
import com.airlab.musicplayer.model.ResourceInfo;

public class Song extends BaseModel implements IIdAware {

	private static final long serialVersionUID = -7901668238613338691L;

	private long id;
	private String songName; // 歌曲名
	private String singerName; // 歌手
	private String songType = "default"; // 歌曲类型
	private String posterResourceId; // 海报资源
	private String songResourceId; // 歌曲资源
	private String lyricsResourceId; // 歌词资源

	private ResourceInfo posterResourceInfo; // 海报资源信息，显示用
	private ResourceInfo songResourceInfo; // 歌曲资源信息，显示用
	private ResourceInfo lyricsResourceInfo; // 歌词资源信息，显示用

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getSingerName() {
		return singerName;
	}

	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}

	public String getSongType() {
		return songType;
	}

	public void setSongType(String songType) {
		this.songType = songType;
	}

	public String getPosterResourceId() {
		return posterResourceId;
	}

	public void setPosterResourceId(String posterResourceId) {
		this.posterResourceId = posterResourceId;
	}

	public String getSongResourceId() {
		return songResourceId;
	}

	public void setSongResourceId(String songResourceId) {
		this.songResourceId = songResourceId;
	}

	public String getLyricsResourceId() {
		return lyricsResourceId;
	}

	public void setLyricsResourceId(String lyricsResourceId) {
		this.lyricsResourceId = lyricsResourceId;
	}

	public ResourceInfo getPosterResourceInfo() {
		return posterResourceInfo;
	}

	public void setPosterResourceInfo(ResourceInfo posterResourceInfo) {
		this.posterResourceInfo = posterResourceInfo;
	}

	public ResourceInfo getSongResourceInfo() {
		return songResourceInfo;
	}

	public void setSongResourceInfo(ResourceInfo songResourceInfo) {
		this.songResourceInfo = songResourceInfo;
	}

	public ResourceInfo getLyricsResourceInfo() {
		return lyricsResourceInfo;
	}

	public void setLyricsResourceInfo(ResourceInfo lyricsResourceInfo) {
		this.lyricsResourceInfo = lyricsResourceInfo;
	}

}
