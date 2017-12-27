package search.service;

import search.pojo.CacheableObject;

import java.util.Map;

/**
 * Created by razvan.popa on 27-Dec-17.
 */
public class RefreshCacheService implements Runnable {

	private CacheableSearchService searchService;

	public RefreshCacheService(CacheableSearchService searchService) {
		this.searchService = searchService;
	}

	@Override
	public void run() {
		SimpleCacheService cacheService = searchService.getCacheService();
		for (Map.Entry<String, CacheableObject> cacheEntry : cacheService.getCacheMap().entrySet()) {

			Object searchResult = searchService.searchInDb(cacheEntry.getKey());
			cacheEntry.getValue().setObject(searchResult);
		}
	}
}
