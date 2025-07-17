//package com.umax.cache.event.core.registry;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Profile;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.Collections;
//import java.util.Set;
//import java.util.stream.Collectors;
//
///**
// * @author wangyingbo
// * @date 2025-07-10 10:20
// */
//// Redis实现
//@Component
//@Profile("redis")
//public class RedisCacheDependencyRegistry implements CacheDependencyRegistry {
//
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
//
//    // Redis key前缀
//    private static final String DEPENDENCY_PREFIX = "cache:dependency:";
//    private static final String REVERSE_DEPENDENCY_PREFIX = "cache:reverse_dependency:";
//
//    public void registerDependency(String sourceCache, String... dependentCaches) {
//        if (sourceCache == null || dependentCaches == null) {
//            return;
//        }
//
//        String dependencyKey = DEPENDENCY_PREFIX + sourceCache;
//        redisTemplate.opsForSet().add(dependencyKey, dependentCaches);
//
//        // 注册反向依赖
//        for (String dependentCache : dependentCaches) {
//            String reverseKey = REVERSE_DEPENDENCY_PREFIX + dependentCache;
//            redisTemplate.opsForSet().add(reverseKey, sourceCache);
//        }
//    }
//
//    public Set<String> getDependencies(String cacheName) {
//        String key = DEPENDENCY_PREFIX + cacheName;
//        Set<Object> members = redisTemplate.opsForSet().members(key);
//        return members != null ?
//                members.stream().map(Object::toString).collect(Collectors.toSet()) :
//                Collections.emptySet();
//    }
//
//    public Set<String> getReverseDependencies(String cacheName) {
//        String key = REVERSE_DEPENDENCY_PREFIX + cacheName;
//        Set<Object> members = redisTemplate.opsForSet().members(key);
//        return members != null ?
//                members.stream().map(Object::toString).collect(Collectors.toSet()) :
//                Collections.emptySet();
//    }
//
//    public void clearAll() {
//        // 注意：生产环境慎用，会清除所有依赖关系
//        Set<String> keys = redisTemplate.keys(DEPENDENCY_PREFIX + "*");
//        if (keys != null) redisTemplate.delete(keys);
//
//        keys = redisTemplate.keys(REVERSE_DEPENDENCY_PREFIX + "*");
//        if (keys != null) redisTemplate.delete(keys);
//    }
//}
