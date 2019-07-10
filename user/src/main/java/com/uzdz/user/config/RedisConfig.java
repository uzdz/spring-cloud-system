package com.uzdz.user.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * redis client config
 * @author uzdz
 * @date: 2019/7/10 11:07
 * @since 0.1.0
 */
@Component
public class RedisConfig {

    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private int port;

    @Bean
    public RedisClient createClient() {
        RedisURI redisURI = RedisURI.create(host, port);

        return RedisClient.create(redisURI);
    }
}
