/**
 * 
 */
package parrot.rest.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import parrot.rest.common.Phrase;

/**
 * @author David Gamez
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PhraseRepositoryRedisImplTest {

	@InjectMocks
	PhraseRepositoryRedisImpl fixture;
	
	@Mock
	RedisTemplate<String, Phrase> redisTemplate;
	
	private HashOperations<String, Object, Object> opsHash;
	
	@Before
	public void setup() {
		assertNotNull(redisTemplate);
		opsHash = new HashOperationsMock();
		when(redisTemplate.opsForHash()).thenReturn(opsHash);
	}
	
	@Test
	public void testSaveLoad() {
		Phrase phrase = new Phrase();
		phrase.setAppContext("appTest");
		phrase.setUrl("url");
		
		assertNull(fixture.load(fixture.getId(phrase)));
		
		fixture.save(phrase);
		
		assertNotNull(opsHash.get(Phrase.class.getName(), fixture.getId(phrase)));
		assertEquals(phrase, opsHash.get(Phrase.class.getName(), fixture.getId(phrase)));
		
		assertEquals(phrase, fixture.load(fixture.getId(phrase)));
	}
	
}
