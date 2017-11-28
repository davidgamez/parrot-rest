/**
 * 
 */
package parrot.rest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import parrot.rest.common.Phrase;

/**
 * @author David Gamez
 *
 */
@Component
@ConditionalOnProperty(name = "persistent.type", havingValue = "REDIS")
public class PhraseRepositoryRedisImpl extends PhraseRepositoryBase {

	@Autowired
	RedisTemplate<String, Phrase> redisTemplate;

	private HashOperations<String, Object, Object> getHashOps() {
		return redisTemplate.opsForHash();
	}

	/* (non-Javadoc)
	 * @see parrot.rest.repository.PhraseRepository#save(parrot.rest.common.Phrase)
	 */
	@Override
	public Phrase save(Phrase phrase) {
		getHashOps().put(Phrase.class.getName(), getId(phrase), phrase);
		return phrase;
	}

	/* (non-Javadoc)
	 * @see parrot.rest.repository.PhraseRepository#load(java.lang.String, org.springframework.http.HttpMethod)
	 */
	@Override
	public Phrase load(String url, HttpMethod httpMethod) {
		return (Phrase) getHashOps().get(Phrase.class.getName(), getIdFromUrl(url));
	}

}
