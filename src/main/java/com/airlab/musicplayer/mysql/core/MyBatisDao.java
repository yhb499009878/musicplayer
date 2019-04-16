package com.airlab.musicplayer.mysql.core;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.airlab.musicplayer.model.IIdAware;
import com.google.common.collect.Lists;

/**
 * Mybatis分页查询工具类,为分页查询增加传递:
 * startRow,endRow : 用于oracle分页使用,从1开始
 * offset,limit : 用于mysql 分页使用,从0开始
 * 
 * @author badqiu
 *
 */
@SuppressWarnings("unchecked")
public abstract class MyBatisDao<DataType extends IIdAware> extends BaseDao implements IDao<DataType> {
	
	protected abstract String getNameSpace();

	@Override
	public int deleteById(long id) {
		int deleteCount = this.getSqlSession().delete(getNameSpace()+".deleteById", id);
		return deleteCount;
	}

	@Override
	public int deleteByIdList(List<Long> idList) {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("idList", idList);
		int deleteCount = this.getSqlSession().delete(getNameSpace()+".deleteByIdList", params);
		return deleteCount;
	}
	
	@Override
	public DataType insert(DataType record) {
		this.getSqlSession().insert(getNameSpace()+".insert", record);
		return record;
	}

	@Override
	public DataType selectById(long id) {
		DataType cachedData = (DataType)this.getSqlSession().selectOne(getNameSpace()+".selectById", id);
		return cachedData;
	}

	@Override
	public int updateById(DataType record) {
		return this.getSqlSession().update(getNameSpace()+".updateById",record);
	}

	@Override
	public DataType saveOrUpdate(DataType record) {
		if(record.getId()==0L)
		{
			this.insert(record);
		}
		else
		{
			this.updateById(record);
		}
		return record;
	}

	@Override
	public List<DataType> queryByIdList(List<Long> idList) {
		return sortByIdList(idList, batchQueryByParamList(idList, paramList->queryByIdListUnordered(idList)));
	}
	
	protected List<DataType> queryByIdListUnordered(List<Long> idList){
		if(CollectionUtils.isEmpty(idList)){
			return Lists.newArrayList();
		}
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("idList", idList);
		return this.getSqlSession().selectList(getNameSpace()+".queryByIdList", params);
	}
	
	@Override
	public long getMaxId() {
		return (long)this.getSqlSession().selectOne(getNameSpace() + ".getMaxId");
	}

	@Override
	public List<DataType> queryByIdRange(Long startId, Long endId) {
		Map params = new HashMap();
		params.put("startId", startId);
		params.put("endId", endId);
		List<DataType> list = this.getSqlSession().selectList(getNameSpace() + ".queryByIdRange",params);
		return list;
	}
}
