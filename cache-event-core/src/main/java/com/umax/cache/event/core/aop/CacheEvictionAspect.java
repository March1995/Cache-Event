//package com.umax.cache.event.core.aop;
//
//import com.umax.cache.event.common.annotations.CascadingCacheEvict;
//import com.umax.cache.event.core.registry.CacheDependencyRegistry;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.Cache;
//import org.springframework.cache.CacheManager;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Method;
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * @author wangyingbo
// * @date 2025-07-10 11:00
// */
//@Aspect
//@Component
//public class CacheEvictionAspect {
//
//    @Autowired
//    private CacheManager cacheManager;
//
//    @Autowired
//    private CacheDependencyRegistry dependencyRegistry;
//
//    @AfterReturning("@annotation(cascadingEvict)")
//    public void processCascadingEvict(JoinPoint jp, CascadingCacheEvict cascadingEvict) {
//        if (!cascadingEvict.cascade()) {
//            return;
//        }
//
//        Method method = ((MethodSignature) jp.getSignature()).getMethod();
//        String[] cacheNames = cascadingEvict.value();
//        String key = parseKey(cascadingEvict.key(), method, jp.getArgs());
//
//        for (String cacheName : cacheNames) {
//            // 清除主缓存
//            evictCache(cacheName, key, cascadingEvict.allEntries());
//
//            // 查找并清除所有依赖缓存
//            Set<String> allDependencies = findAllDependentCaches(cacheName);
//            allDependencies.forEach(depCache -> {
//                evictCache(depCache, key, cascadingEvict.allEntries());
//            });
//        }
//    }
//
//    private Set<String> findAllDependentCaches(String cacheName) {
//        return dependencyRegistry.getDependentCaches(cacheName);
//    }
//
//    private void evictCache(String cacheName, String key, boolean allEntries) {
//        Cache cache = cacheManager.getCache(cacheName);
//        if (cache != null) {
//            if (allEntries) {
//                cache.clear();
//            } else if (key != null) {
//                cache.evict(key);
//            }
//        }
//    }
//
//    private String parseKey(String keyExpression, Method method, Object[] args) {
//        // 实现SpEL解析逻辑
//        // 可以使用Spring的ExpressionEvaluator
//        return keyExpression; // 简化实现
//    }
//}
