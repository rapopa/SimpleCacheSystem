import org.junit.Assert;
import org.junit.Test;

/**
 * Created by admin on 27-Dec-17.
 */
public class TestCache {

	@Test
	public void testEntryCachedAfterSearch() throws Exception {
		DBMock dbMock = new DBMock();
		Assert.assertEquals(0, dbMock.getCacheService().getCacheMap().size());
		dbMock.search("Ilie");
		Assert.assertEquals(1, dbMock.getCacheService().getCacheMap().size());
	}

	@Test
	public void testEntryRemovedFromCacheAfterDeadline() throws Exception {
		DBMock dbMock = new DBMock();
		Assert.assertEquals(0, dbMock.getCacheService().getCacheMap().size());
		dbMock.search("Ilie");
		Assert.assertEquals(1, dbMock.getCacheService().getCacheMap().size());
		Thread.sleep(11000);
		Assert.assertEquals(0, dbMock.getCacheService().getCacheMap().size());
	}

	@Test
	public void testMultipleSearches() throws Exception {
		DBMock dbMock = new DBMock();
		Assert.assertEquals(0, dbMock.getCacheService().getCacheMap().size());
		dbMock.search("Ilie");
		dbMock.search("Andrei");
		Thread.sleep(2000);
		dbMock.search("Ilie");
		dbMock.search("Ilie");
		dbMock.search("Ilie");
		Thread.sleep(5000);
		dbMock.search("Ilie");

		Assert.assertEquals(2, dbMock.getCacheService().getCacheMap().size());
		Thread.sleep(5000);
		Assert.assertEquals(1, dbMock.getCacheService().getCacheMap().size());
	}
}
