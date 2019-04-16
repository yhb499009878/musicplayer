package com.airlab.musicplayer.mysql.api;

import java.util.List;

import com.airlab.musicplayer.model.ResourceInfo;
import com.airlab.musicplayer.mysql.core.IDao;

public interface IResourceInfoDao extends IDao<ResourceInfo>{
	public ResourceInfo selectByResourceId(String resourceId);
	
	public List<ResourceInfo> selectByResourceIdList(List<String> resourceIdList);
	
	public List<ResourceInfo> queryPartsResourceInfoByResourceId(String resourceId);
	
	public int deletePartsResourceInfoByResourceId(String resourceId);
	
	public List<ResourceInfo> queryByParams(String resourceId, String md5, String fileName, int status);
}
