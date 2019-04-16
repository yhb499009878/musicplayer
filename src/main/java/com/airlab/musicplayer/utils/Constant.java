package com.airlab.musicplayer.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Constant extends Configure{
	public static Map<String,Object> getProperties(){
		Map<String,Object> map=new HashMap<>();
		Iterator<String> keyIterator=config.getKeys();
		while(keyIterator.hasNext()){
			String key=keyIterator.next();
			if(!key.contains("password")){
				map.put(key, config.getProperty(key));
			}
			
		}
		return map;
	}
	
	public static String getConfigFile(){
		return config.getBasePath();
	}
	
	/**
	 * 重载配置文件的时间间隔，默认60s
	 * @return
	 */
	public static long getReloadPropertiesInterval(){ return getProperty("reload.properties.interval",60000); }
	
	/**
	 * 资源相关配置
	 * @return
	 */
	public static final String FILE_ROOT_PATH = getProperty("file.root.path","/opt/airlab/musicplayer/data/");
	//资源文件下载地址头
	public static final  String RESOURCE_DOWNLOAD_ADDRESS = getProperty("http.download.address","http://localhost/");
	//本地资源文件下载地址头
	public static final  String STATIC_RESOURCE_DOWNLOAD_ADDRESS = getProperty("static.resource.download.address","http://localhost/");
	//资源大小限制（100M）
	public static final long getResourceMaxSize(){return getProperty("resource.max.size", 100*1024*1024);};	

	//默认分页大小
	public static final int PAGE_SIZE = 6;
	
	//mysql 相关配置
	public static final int DATA_BATCH_SIZE=getProperty("data.batch.size",5000);
	
}
