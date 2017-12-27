package search.pojo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by razvan.popa on 27-Dec-17.
 * @param <T>    the object that is cached
 */
public class CacheableObject<T> {

	private LocalDateTime lastSearchDate = LocalDateTime.now();

	private T object;

	public CacheableObject(T object) {
		this.object = object;
	}

	public List<T> getResult() {
		if (object instanceof Collection<?>) {
			return new ArrayList<T>((Collection<? extends T>) this.object);
		}
		List<T> result = new ArrayList<T>();
		result.add(this.object);
		return result;
	}

	public LocalDateTime getLastSearchDate() {
		return lastSearchDate;
	}

	public void resetLastSearchDate() {
		this.lastSearchDate = LocalDateTime.now();
	}

	public void setObject(T object) {
		this.object = object;
	}
}
