package com.rsoft.hurmanmobileapp.component;
import io.lettuce.core.event.connection.ConnectedEvent;
import io.lettuce.core.event.connection.DisconnectedEvent;
import org.springframework.data.redis.connection.ReactiveRedisConnection;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.stereotype.Component;
import org.springframework.context.event.EventListener;

@Component
public class RedisConnectionListener {

    private final ReactiveRedisConnectionFactory connectionFactory;
    private boolean isRedisDown = false;

    public RedisConnectionListener(ReactiveRedisConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @EventListener
    public void handleRedisConnectedEvent(ConnectedEvent event) {
        isRedisDown = false;
    }

    @EventListener
    public void handleRedisDisconnectedEvent(DisconnectedEvent event) {
        isRedisDown = true;
    }

    public void checkAndFlushRedis() {
        if (isRedisDown) {
            try {
                ReactiveRedisConnection connection = connectionFactory.getReactiveConnection();
                connection.serverCommands().flushAll().subscribe();
                System.out.println("Redis cache cleared after reconnect.");
            } catch (Exception e) {
                System.out.println("Failed to reconnect and flush Redis.");
            }
        }
    }
}