package com.airlab.musicplayer.mysql.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.airlab.musicplayer.model.IIdAware;
import com.airlab.musicplayer.model.Page;
import com.google.common.collect.Lists;

public class BaseDao extends SqlSessionDaoSupport {


	public <T> Page<T> selectPage(Page<T> page, final String statementName, Object parameter) {
		String countStatementName = statementName + ".count";
		return selectPage(page, statementName, countStatementName, parameter);
	}

	public <T> Page<T> selectPage(Page<T> page, final String statementName,final String countStatementName, Object parameter) {

		Number totalItems = (Number) getSqlSession().selectOne(countStatementName, parameter);

		if (totalItems != null && totalItems.longValue() > 0) {
			List<T> list = getSqlSession().selectList(statementName, toParameterMap(parameter, page));
			page.setResult(list);
			page.setTotalItems(totalItems.longValue());
		}
		return page;
	}

	protected static Map toParameterMap(Object parameter, Page p) {
		Map map = toParameterMap(parameter);
		map.put("offset", p.getOffset());
		map.put("limit", p.getPageSize());
		return map;
	}

	protected static Map toParameterMap(Object parameter) {
		if (parameter instanceof Map) {
			return (Map) parameter;
		} else {
			return null;
		}
	}

	@Override
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	/**
	 * 分批查询，当参数列表很大时，将参数列表分成多个子列表,分批执行后合并结果并返回。结果列表是无序的。
	 * @param paramList 参数列表
	 * @param func 具体查询的function
	 * @return 结果列表
	 */
	public static <P,R> List<R> batchQueryByParamList(List<P> paramList,Function<List<P>,List<R>> func) {
		if(CollectionUtils.isEmpty(paramList)){
			return Lists.newArrayList();
		}
		
    	int count=paramList.size();
    	if(count<=getBatchSize()){
    		return func.apply(paramList);
    	}else{
    		final List<R> totalList = Lists.newArrayList();
			List<List<P>> taskList = splitTask(paramList, count);
			taskList.parallelStream().forEach(
					pList -> totalList.addAll(func.apply(pList)));
			return totalList;
    	}
	}
	
	/**
	 * 切分待处理数据列表为多个任务列表
	 * 
	 * @param paramList 待处理数据列表
	 * @param count 待处理数据数量
	 * @return 待处理数据任务列表，每个任务含一个待处理数据列表
	 */
	public static <P> List<List<P>> splitTask(List<P> paramList, int count) {
		int batch = computeBatch(count);
		List<List<P>> taskList=Lists.newArrayList();
		for (int i = 0; i < batch; i++) {
			int start = i * getBatchSize();
			int end = computeEndIndex(count, i);
			taskList.add(paramList.subList(start, end));
		}
		return taskList;
	}
	
	private static int computeEndIndex(int count, int i) {
		int end=getBatchSize()*(i+1);
		if(end>count){
			end=count;
		}
		return end;
	}

	private static int computeBatch(int count) {
		int batch = count / getBatchSize();
		if (count % getBatchSize() > 0) {
			batch++;
		}
		return batch;
	}

	private static int getBatchSize() {
		return 200;
	}
	

	public static <T extends IIdAware> List<T> sortByIdList(List<Long> idList,
			List<T> resultList) {
		if(CollectionUtils.isEmpty(idList)){
			return Lists.newArrayList();
		}
		Map<Long, T> objById = new HashMap<Long, T>();
		for (T obj : resultList) {
			objById.put(obj.getId(), obj);
		}
		return idList.stream().map(objById::get).filter(obj->obj!= null).collect(Collectors.toList());
	}
}
