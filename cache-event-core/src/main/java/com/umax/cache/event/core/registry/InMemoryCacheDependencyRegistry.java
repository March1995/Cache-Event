package com.umax.cache.event.core.registry;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangyingbo
 * @date 2025-07-10 09:54
 */
// 内存实现
@Component
//@Profile("!redis")
public class InMemoryCacheDependencyRegistry implements CacheDependencyRegistry {

    /**
     * 依赖关系映射：
     * Key: 被依赖的缓存名称
     * Value: 依赖该缓存的所有缓存集合
     * <p>
     * 例如：{ "categoryTree" : ["productList", "promotionList"] }
     * 表示productList和promotionList都依赖categoryTree
     */
    private final Map<String, Set<String>> dependencyMap = new ConcurrentHashMap<>();

    // 存储完全展开的依赖关系
    private final Map<String, Set<String>> expandedDependencies = new ConcurrentHashMap<>();
    // 标记已处理的缓存
    private final Set<String> processedCaches = ConcurrentHashMap.newKeySet();

    /**
     * 注册缓存依赖关系
     *
     * @param dependentCache   当前缓存（依赖方）
     * @param cachesToDependOn 所依赖的缓存（被依赖方）
     */
    public void registerDependency(String dependentCache, String... cachesToDependOn) {
        for (String cacheToDepend : cachesToDependOn) {
            dependencyMap.computeIfAbsent(cacheToDepend, k -> ConcurrentHashMap.newKeySet())
                    .add(dependentCache);
            processedCaches.remove(dependentCache); // 标记需要重新展开
        }
    }

    /**
     * 获取所有依赖（按需展开）
     */
    public Set<String> getDependentCaches(String cacheName) {
        // 如果尚未展开，立即处理
        if (!processedCaches.contains(cacheName)) {
            expandDependencies(cacheName);
        }
        return expandedDependencies.getOrDefault(cacheName, Collections.emptySet());
    }

    /**
     * 递归展开依赖关系
     */
    private void expandDependencies(String cacheName) {
        Set<String> allDependants = new HashSet<>();
        Stack<String> stack = new Stack<>();
        stack.push(cacheName);

        while (!stack.isEmpty()) {
            String current = stack.pop();
            Set<String> directDeps = dependencyMap.get(current);
            if (directDeps != null) {
                for (String dep : directDeps) {
                    if (allDependants.add(dep)) {
                        stack.push(dep);
                    }
                }
            }
        }
        expandedDependencies.put(cacheName, allDependants);
        processedCaches.add(cacheName);
    }


//    /**
//     * 获取所有依赖指定缓存的缓存列表
//     *
//     * @param cacheName 被依赖的缓存名称
//     * @return 依赖该缓存的所有缓存集合
//     */
//    public Set<String> getDependentCaches(String cacheName) {
//        return dependencyMap.getOrDefault(cacheName, Collections.emptySet());
//    }

    /**
     * 清除所有依赖关系（测试用）
     */
    public void clearAll() {
        dependencyMap.clear();
    }
}
