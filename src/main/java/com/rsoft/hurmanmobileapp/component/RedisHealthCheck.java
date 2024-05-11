package com.rsoft.hurmanmobileapp.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RedisHealthCheck {
    private final RedisConnectionListener redisConnectionListener;

    public RedisHealthCheck(RedisConnectionListener redisConnectionListener) {
        this.redisConnectionListener = redisConnectionListener;
    }

    @Scheduled(fixedRate = 5000)
    public void performHealthCheck() {
        redisConnectionListener.checkAndFlushRedis();
    }
}