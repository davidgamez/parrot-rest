/**
 * 
 */
package parrot.rest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import parrot.rest.common.Phrase;

/**
 * @author David Gamez, Isuru Weerasooriya
 *
 */
@Component
@ConditionalOnProperty(name="persistent.type", havingValue = "REDIS")
public class PhraseRepositoryRedisImpl extends PhraseRepositoryBase {
  
	@Autowired
	RedisTemplate<String, Phrase> redisTemplate;

    private HashOperations<String, Object, Object> getHashOps() {
        return redisTemplate.opsForHash();
    }
	
	@Override
	public Phrase save(Phrase phrase) {
		getHashOps().put(Phrase.class.getName(), getId(phrase), phrase);
		return phrase;
	}

	@Override
	public Phrase load(String url) {
		return (Phrase) getHashOps().get(Phrase.class.getName(), getIdFromUrl(url));
	}
	
	@Override
	public Phrase delete(String url) {
		Phrase result = load(url);
		if (result != null) {
			getHashOps().delete(Phrase.class.getName(), getIdFromUrl(url));
		}
		return result;
	}
	
}
