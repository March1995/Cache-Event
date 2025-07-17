package com.umax.cache.event.core.registry;

import java.util.Set;

/**
 * @author wangyingbo
 * @date 2025-07-10 10:19
 */
public interface CacheDependencyRegistry {

    void registerDependency(String sourceCache, String... dependentCaches);

    Set<String> getDependentCaches(String cacheName);

    void clearAll();
}
