package com.airlab.musicplayer.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class TimeLimitedLRUCache<K,V> {
	private static class CacheObj<V> {
		private long cacheTime;
		private V value;
	}
	
	volatile private Map<K,CacheObj<V>> innercache;
	private long delay;
	private static final Timer timer = TimerManager.getTimer("TimeLimitedLRUMap SelfRemove");
	private TimerTask overTimeTask;
    int maxSize =0;
	public TimeLimitedLRUCache() {
		this(8);
	}

	public int size() {
		return this.innercache.size();
	}
	public TimeLimitedLRUCache(int maxSize) {
		this(maxSize,60 *1000);
	}
	
	public TimeLimitedLRUCache(int maxSize,long delay) {
		this.delay = delay;
		this.maxSize=maxSize;
		if(maxSize<=0) {
			innercache = new HashMap<K,CacheObj<V>>(0);
		} else {
			innercache = new HashMap<K,CacheObj<V>>(maxSize);
		}
		overTimeTask = new TimerTask() {
			@Override
			public void run() {
				checkOverTime();
			}
		};
		timer.schedule(overTimeTask, delay, 10000);
	}
	public synchronized V put(K key, V value) {
		if(maxSize<=0) return null;
		CacheObj<V> obj = innercache.get(key);
		V oldValue=null;
		if(obj == null) {
			obj = new CacheObj<V>();
		} else {
			oldValue=obj.value;
		}
		obj.cacheTime=System.currentTimeMillis();
		obj.value=value;
		return oldValue;
	}
	public synchronized void refresh(K key,V value) {
		CacheObj<V> obj = innercache.get(key);
		if(obj != null) {
		} else {
			obj = new CacheObj<V>();
			obj.value=value;
			innercache.put(key, obj);
		}
		obj.cacheTime=System.currentTimeMillis();
	}
	public synchronized V get(K key) {
		if(maxSize<=0) return null;
		CacheObj<V> obj = innercache.get(key);
		if(obj == null) {
			return null;
		} else {
			return obj.value;
		}
	}
	
	public synchronized V remove(K key) {
		if(maxSize<=0) return null;
		CacheObj<V> obj = innercache.remove(key);
		if(obj != null) {
			return obj.value;
		}
		return null;
	}

	private synchronized void checkOverTime() {
		long current = System.currentTimeMillis();
		Set<Entry<K, CacheObj<V>>> entrySet = new HashSet<>(innercache.entrySet());
		for(Entry<K, CacheObj<V>> entry:entrySet) {
			CacheObj<V> obj = entry.getValue();
			if(current-obj.cacheTime>delay) {//remove
				innercache.remove(entry.getKey());
			}
		}
	}

	public synchronized boolean containsKey(K key) {
		if(maxSize<=0) return false;
		return innercache.containsKey(key);
	}

	public synchronized void destroy() {
		overTimeTask.cancel();
	}
}
