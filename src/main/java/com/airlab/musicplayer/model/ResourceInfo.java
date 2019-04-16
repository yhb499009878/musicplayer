package com.airlab.musicplayer.model;

import org.springframework.stereotype.Component;

@Component
public class ResourceInfo extends BaseModel implements IIdAware {

	private static final long serialVersionUID = -3983447220983555258L;

	private long id;

	private String resourceId;

	private String fileName;

	private String systemFileName;

	private Long userId;

	private String mimeType;

	private long size = 0;

	private String filePath;

	private String md5;

	private byte[] content;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSystemFileName() {
		return systemFileName;
	}

	public void setSystemFileName(String systemFileName) {
		this.systemFileName = systemFileName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

}
