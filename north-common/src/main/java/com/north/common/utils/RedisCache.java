package com.north.common.utils;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Component
@AllArgsConstructor
public class RedisCache {

    private final RedisTemplate<String, Object> redisTemplate;

    public <T> void setCacheObject(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public <T> void setCacheObject(
            String key,
            T value,
            Duration timeout) {
        redisTemplate.opsForValue().set(key, value, timeout);
    }

    @SuppressWarnings("unchecked")
    public <T> T getCacheObject(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    public boolean deleteObject(String key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

    public boolean expire(String key, Duration timeout) {
        return Boolean.TRUE.equals(redisTemplate.expire(key, timeout));
    }

    public Long increment(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    public Long decrement(String key, long delta) {
        return redisTemplate.opsForValue().decrement(key, delta);
    }

    public <T> void setCacheMap(String key, Map<String, T> data) {
        redisTemplate.opsForHash().putAll(key, data);
    }

    @SuppressWarnings("unchecked")
    public <T> T getCacheMapValue(
            String key,
            String hashKey) {
        return (T) redisTemplate.opsForHash().get(key, hashKey);
    }

    public Set<String> getKeys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    public void deleteObjects(Collection<String> keys) {
        redisTemplate.delete(keys);
    }
}
