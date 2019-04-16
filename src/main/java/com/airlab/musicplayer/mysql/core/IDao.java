package com.airlab.musicplayer.mysql.core;

import java.util.List;

/**
 * mybatis 通用方法接口
 * @author Administrator
 *
 * @param <DataType>
 */
public interface IDao<DataType>  {

	int deleteByIdList(List<Long> idList);
	
	int deleteById(long id);

	DataType insert(DataType record);
	
	int updateById(DataType record);

	DataType selectById(long id);

	DataType saveOrUpdate(DataType record);

	/**
	 * 结果顺序和 id 列表顺序一致，但不含查不到的 id
	 * @param idList
	 * @return
	 */
	List<DataType> queryByIdList(List<Long> idList);
	
	long getMaxId();
	
	/**
	 * 通过ID范围查询数据，包含startId和endId
	 * @param startId
	 * @param endId
	 * @return
	 */
	List<DataType> queryByIdRange(Long startId, Long endId);
}