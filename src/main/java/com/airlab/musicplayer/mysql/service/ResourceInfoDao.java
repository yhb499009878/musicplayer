package com.airlab.musicplayer.mysql.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.airlab.musicplayer.model.ResourceInfo;
import com.airlab.musicplayer.mysql.api.IResourceInfoDao;
import com.airlab.musicplayer.mysql.core.MyBatisDao;

@Component
public class ResourceInfoDao extends MyBatisDao<ResourceInfo>implements IResourceInfoDao {
	private static final String NAMESPACE="mapper.ResourceInfoMapper";
	
	@Override
	protected String getNameSpace() {
		return NAMESPACE;
	}
	
	@Override
	public ResourceInfo selectByResourceId(String resourceId) {
		return this.getSqlSession().selectOne(NAMESPACE+".selectByResourceId", resourceId);
	}

	
	@Override
	public List<ResourceInfo> queryPartsResourceInfoByResourceId(
			String resourceId) {
		return this.getSqlSession().selectList(NAMESPACE+".queryPartsResourceInfoByResourceId", resourceId);
	}

	@Override
	public int deletePartsResourceInfoByResourceId(
			String resourceId) {
		return this.getSqlSession().delete(NAMESPACE+".deletePartsResourceInfoByResourceId", resourceId);
	}

	@Override
	public List<ResourceInfo> queryByParams(String resourceId, String md5,
			String fileName, int status) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", status);
		
		if(StringUtils.isNotEmpty(resourceId)){
			params.put("resourceId", resourceId);
		}
		
		if(StringUtils.isNotEmpty(md5)){
			params.put("md5", md5);
		}
		
		if(StringUtils.isNotEmpty(fileName)){
			params.put("fileName", fileName);
		}
		
		return this.getSqlSession().selectList(NAMESPACE+".queryByParams", params);
	}

	@Override
	public List<ResourceInfo> selectByResourceIdList(List<String> resourceIdList) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("resourceIdList", resourceIdList);
		return this.getSqlSession().selectList(this.getNameSpace() + ".selectByResourceIdList", params);
	}

	
}
