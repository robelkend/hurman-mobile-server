package com.rsoft.hurmanmobileapp.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void clearCache() {
        redisTemplate.getConnectionFactory().getConnection().flushDb();
    }
}