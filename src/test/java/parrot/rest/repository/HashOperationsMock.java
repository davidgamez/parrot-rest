package parrot.rest.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.ScanOptions;

public class HashOperationsMock implements HashOperations<String, Object, Object> {

	private Map<String, Map<Object, Object>> map = new HashMap<>();
	
	@Override
	public void put(String key, Object hashKey, Object value) {
		Map<Object, Object> valueMap = map.get(key);
		if (valueMap == null) {
			valueMap = new HashMap<>();
			map.put(key, valueMap);
		}
		valueMap.put(hashKey, value);
	}

	@Override
	public Object get(String key, Object hashKey) {
		Map<Object, Object> valueMap = map.get(key);
		if (valueMap != null) {
			return valueMap.get(hashKey);
		}
		
		return null;
	}
	
	@Override
	public Long delete(String key, Object... hashKeys) {
		Map<Object, Object> valueMap = map.get(key);
		if (valueMap != null) {
			for (Object hashKey : hashKeys) {
				valueMap.remove(hashKey);
			}
		}
		return null;
	}

	@Override
	public Map<Object, Object> entries(String key) {
		throw new UnsupportedOperationException("Unsupported method");
	}

	@Override
	public RedisOperations<String, ?> getOperations() {
		throw new UnsupportedOperationException("Unsupported method");
	}

	@Override
	public Boolean hasKey(String key, Object hashKey) {
		throw new UnsupportedOperationException("Unsupported method");
	}

	@Override
	public Long increment(String key, Object hashKey, long delta) {
		throw new UnsupportedOperationException("Unsupported method");
	}

	@Override
	public Double increment(String key, Object hashKey, double delta) {
		throw new UnsupportedOperationException("Unsupported method");
	}

	@Override
	public Set<Object> keys(String key) {
		throw new UnsupportedOperationException("Unsupported method");
	}

	@Override
	public List<Object> multiGet(String key, Collection<Object> hashKeys) {
		throw new UnsupportedOperationException("Unsupported method");
	}

	@Override
	public void putAll(String key, Map<? extends Object, ? extends Object> m) {
		throw new UnsupportedOperationException("Unsupported method");
	}

	@Override
	public Boolean putIfAbsent(String key, Object hashKey, Object value) {
		throw new UnsupportedOperationException("Unsupported method");
	}

	@Override
	public Cursor<Entry<Object, Object>> scan(String key, ScanOptions options) {
		throw new UnsupportedOperationException("Unsupported method");
	}

	@Override
	public Long size(String key) {
		throw new UnsupportedOperationException("Unsupported method");
	}

	@Override
	public List<Object> values(String key) {
		throw new UnsupportedOperationException("Unsupported method");
	}

}
