import search.service.CacheableSearchService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 27-Dec-17.
 */
public class DBMock extends CacheableSearchService {

	private Map<String, String> db = new HashMap<>();

	public DBMock() {
		db.put("Ion", "Ion");
		db.put("Vasile", "Vasile");
		db.put("Gheorghe", "Gheorghe");
		db.put("Vlad", "Vlad");
		db.put("Andrei", "Andrei");
		db.put("Bogdan", "Bogdan");
		db.put("Razvan", "Razvan");
		db.put("Ilie", "Ilie");
	}
	@Override
	protected Object searchInDb(String searchString) {
		return db.get(searchString);
	}
}
