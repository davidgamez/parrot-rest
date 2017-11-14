/**
 * 
 */
package parrot.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import parrot.rest.common.Phrase;
import parrot.rest.controller.TalkController;
import redis.clients.jedis.JedisShardInfo;

/**
 * @author gamezd
 *
 */
@Configuration
public class AppConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(TalkController.class);
	
	@Value("${spring.redis.url}")
	private String redisUrl;
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		logger.info("Creating connection to: {}", redisUrl);
	    JedisShardInfo poolConfig = new JedisShardInfo(redisUrl);
		return new JedisConnectionFactory(poolConfig);
	}
	 
	@Bean
	public RedisTemplate<String, Phrase> redisTemplate() {
	    RedisTemplate<String, Phrase> template = new RedisTemplate<String, Phrase>();
	    template.setConnectionFactory(jedisConnectionFactory());
	    return template;
	}
}
