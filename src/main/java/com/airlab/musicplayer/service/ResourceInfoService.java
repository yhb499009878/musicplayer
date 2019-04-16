package com.airlab.musicplayer.service;

import com.airlab.musicplayer.model.ResourceInfo;

public interface ResourceInfoService {
	/**
	 * 增加资源
	 * 返回的下载地址为绝对路径
	 * @param sessionId
	 * @param fileName
	 * @param mimeType
	 * @param resourceId
	 * @param fileContent
	 * @return ReturnResult<ResourceModel>
	 */
	public ResourceInfo addResource(Long userId, String fileName, String mimeType, String resourceId, byte[] fileContent);
	
	/**
	 * 获取资源
	 * @param resourceId
	 * @return ReturnResult<ResourceModel>
	 */
	public ResourceInfo getResource(String resourceId);
	
	/**
	 * 查询资源短信息
	 * @param resourceId
	 * @return ReturnResult<ResourceModel>
	 */
	public ResourceInfo queryResourceInfo(String resourceId);
	
	/**
	 * 保存资源
	 * @param resourceInfo
	 * @return
	 */
	public ResourceInfo saveResourceInfo(ResourceInfo resourceInfo);
	
	/**
	 * 查询资源直接返回文件相对路径
	 * @param sessionId
	 * @param resourceId
	 * @return ReturnResult<ResourceModel>
	 */
	public ResourceInfo getResourceInfo(String resourceId);
	
	/**
	 * 获取资源
	 * @param sessionId
	 * @param resourceId
	 * @return ReturnResult<ResourceModel>
	 */
	public byte[] getResourceContent(String filePath);

	
}
