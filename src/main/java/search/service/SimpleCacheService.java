package search.service;

import search.pojo.CacheableObject;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by admin on 27-Dec-17.
 */
public final class SimpleCacheService {

	private Map<String, CacheableObject> cacheMap;

	/**
	 * Do not instantiate SimpleCacheService.
	 */
	public SimpleCacheService() {
		this.cacheMap = new ConcurrentHashMap<String, CacheableObject>();
	}

	public CacheableObject searchInCache(String searchArgument) {
		CacheableObject resultInCache = cacheMap.get(searchArgument);

		if (resultInCache == null) {
			return null;
		}
		resultInCache.resetLastSearchDate();
		return resultInCache;
	}

	protected CacheableObject cache(String searchArgument, Object result) {
		CacheableObject cachedObject = new CacheableObject(result);
		cacheMap.put(searchArgument, cachedObject);
		return cachedObject;
	}

	public Map<String, CacheableObject> getCacheMap() {
		return this.cacheMap;
	}
}
