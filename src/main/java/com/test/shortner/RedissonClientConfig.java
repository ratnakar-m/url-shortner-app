package com.test.shortner;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonClientConfig {

    @Bean
    public RedissonClient init(@Value("${spring.redis.host}") String host, @Value("${spring.redis.port}") String port) {
        Config config = new Config();
        String url = "redis://"+ host + ":" + port;
        config.useSingleServer().setAddress(url);
        return Redisson.create(config);
    }
}
