/**
 * Project Name cmpp-gateway
 * File Name EhCache.java
 * Package Name com.lljqiu.tools.cmpp.gateway.utils
 * Create Time 2018年8月30日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.gateway.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * ClassName: EhCache.java <br>
 * Description: <br>
 * Create by: name：liujie <br>
 * email: liujie@lljqiu.com <br>
 * Create Time: 2018年8月30日<br>
 */
public class EhCache {
	public static final String path = EhCache.class.getClassLoader().getResource("ehcache.xml").getPath();
	public static CacheManager manager = CacheManager.create(path);
	public static final String CACHE_NAME = "gatewayCache";

	public EhCache() {
	}

	/**
	 * Description：获取数据
	 * 
	 * @param cacheName
	 * @param key
	 * @return
	 * @return Object
	 * @author name：liujie <br>
	 * 		email: liujie@lljqiu.com
	 **/
	public static Object get(String cacheName, Object key) {
		Cache cache = manager.getCache(cacheName);
		if (cache != null) {
			Element element = cache.get(key);
			if (element != null)
				return element.getObjectValue();
		}
		return null;
	}

	/**
	 * Description：存储
	 * 
	 * @param cacheName
	 * @param key
	 * @param value
	 * @return void
	 * @author name：liujie <br>
	 * 		email: liujie@lljqiu.com
	 **/
	public static void put(String cacheName, Object key, Object value) {
		Cache cache = manager.getCache(cacheName);
		if (cache != null)
			cache.put(new Element(key, value));
	}

	/**
	 * Description：清除
	 * 
	 * @param cacheName
	 * @param key
	 * @return
	 * @return boolean
	 * @author name：liujie <br>
	 * 		email: liujie@lljqiu.com
	 **/
	public static boolean remove(String cacheName, Object key) {
		Cache cache = manager.getCache(cacheName);
		if (cache != null)
			return cache.remove(key);
		else
			return false;
	}

	/**
	 * Description：清空所有缓存
	 * 
	 * @return void
	 * @author name：liujie <br>
	 * 		email: liujie@lljqiu.com
	 **/
	public static void clearAll() {
		manager.clearAll();
	}
}
