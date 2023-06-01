package com.cocktailgenerator.main;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

@Configuration
public class CacheConfig {

	@Autowired RedisConnectionFactory redisConnectionFactory;
	
	@Bean
	public CacheManager cacheManager() {
		RedisCacheConfiguration cacheConfig = 
				RedisCacheConfiguration.defaultCacheConfig()
					.entryTtl(Duration.ofMinutes(15))
					.disableCachingNullValues()
					.serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
		
		return RedisCacheManager.builder(redisConnectionFactory)
			.cacheDefaults(cacheConfig)
			.transactionAware()
			.build();
	}
}
