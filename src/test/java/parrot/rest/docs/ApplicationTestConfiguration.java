package parrot.rest.docs;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import parrot.rest.common.Phrase;
import parrot.rest.repository.HashOperationsMock;

public class ApplicationTestConfiguration {

	private HashOperations<String, Object, Object> opsHash;
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return mock(JedisConnectionFactory.class);
	}
	 
	@Bean
	public RedisTemplate<String, Phrase> redisTemplate() {
	    @SuppressWarnings("unchecked")
		RedisTemplate<String, Phrase> template = mock(RedisTemplate.class);
	    opsHash = new HashOperationsMock();
		when(template.opsForHash()).thenReturn(opsHash);
	    return template;
	}
}
