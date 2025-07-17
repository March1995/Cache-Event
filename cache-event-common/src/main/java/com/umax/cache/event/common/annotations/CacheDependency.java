package com.umax.cache.event.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记缓存之间的依赖关系
 *
 * @author wangyingbo
 * @date 2025-07-09 17:03
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheDependency {

    /**
     * 当前缓存所依赖的其他缓存名称
     * 当这些缓存失效时，当前缓存也需要失效
     * 关系：当前缓存 → 依赖的其他缓存
     * 示例：
     *
     * @CacheDependency({"categoryTree", "inventoryStatus"})
     * 表示当categoryTree或inventoryStatus失效时，当前缓存也要失效
     */
    String[] value();
}
