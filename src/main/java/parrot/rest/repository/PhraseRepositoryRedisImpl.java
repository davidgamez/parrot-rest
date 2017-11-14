/**
 * 
 */
package parrot.rest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import parrot.rest.common.Phrase;

/**
 * @author David Gamez
 *
 */
@Component
public class PhraseRepositoryRedisImpl implements PhraseRepository {

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

	public String getId(Phrase phrase) {
		return String.format("%s/%s", phrase.getAppContext(), phrase.getUrl());
	}

	@Override
	public Phrase load(String url) {
		return (Phrase) getHashOps().get(Phrase.class.getName(), url);
	}
	

}
