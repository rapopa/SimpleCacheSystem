package search.service;

import search.pojo.CacheableObject;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Created by razvan.popa on 27-Dec-17.
 */
public class CleanupCacheService implements Runnable {

	Duration timeToCache = Duration.ofSeconds(10l);

	private CacheableSearchService searchService;

	public CleanupCacheService(CacheableSearchService searchService) {
		this.searchService = searchService;
	}

	@Override
	public void run() {
		SimpleCacheService cacheService = searchService.getCacheService();
		for (Map.Entry<String, CacheableObject> cacheEntry : cacheService.getCacheMap().entrySet()) {

			CacheableObject entryValue = cacheEntry.getValue();
			if (entryValue == null) {
				cacheService.getCacheMap().remove(cacheEntry.getKey());
				continue;
			}
			LocalDateTime lastSearch = entryValue.getLastSearchDate();
			if (Duration.between(lastSearch, LocalDateTime.now()).compareTo(timeToCache) > 0) {
				cacheService.getCacheMap().remove(cacheEntry.getKey());
			}
		}
	}
}
