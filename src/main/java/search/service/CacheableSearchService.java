package search.service;

import search.pojo.CacheableObject;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 27-Dec-17.
 */
public class CacheableSearchService {

	private SimpleCacheService cacheService = new SimpleCacheService();

	private ScheduledExecutorService refreshExecutor = Executors.newSingleThreadScheduledExecutor();

	private ScheduledExecutorService cleanupExecutor = Executors.newSingleThreadScheduledExecutor();

	public CacheableSearchService() {
		refreshExecutor.scheduleAtFixedRate(new RefreshCacheService(this), 0, 2, TimeUnit.SECONDS);
		refreshExecutor.scheduleAtFixedRate(new CleanupCacheService(this), 0, 1, TimeUnit.SECONDS);

	}
	public final CacheableObject search(String searchString) {
		CacheableObject searchResult = cacheService.searchInCache(searchString);
		if (searchResult == null) {
			Object result = searchInDb(searchString);
			searchResult = cacheService.cache(searchString, result);
		}

		return searchResult;
	}

	protected Object searchInDb(String searchString) {
		// this method does the actual search in the DB.
		// TODO: Implement me
		return null;
	}

	public SimpleCacheService getCacheService() {
		return cacheService;
	}
}
