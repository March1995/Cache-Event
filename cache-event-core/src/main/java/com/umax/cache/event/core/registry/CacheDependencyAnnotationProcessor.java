package com.umax.cache.event.core.registry;

import com.umax.cache.event.common.annotations.CacheDependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author wangyingbo
 * @date 2025-07-10 14:43
 */
@Component
public class CacheDependencyAnnotationProcessor implements BeanPostProcessor {

    @Autowired
    private CacheDependencyRegistry registry;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        // 扫描Bean的所有方法
        for (Method method : bean.getClass().getDeclaredMethods()) {
            // 注意这里 Spring 使用 CGLIB 或 JDK 动态代理创建 Bean 的子类或代理对象，这可能导致：
            //  method.getDeclaringClass() 不是原始类。 实际调用的方法是代理生成的合成方法（synthetic），而非源码中定义的方法
//            CacheDependency cacheDependency = method.getAnnotation(CacheDependency.class);
            CacheDependency cacheDependency = AnnotationUtils.findAnnotation(method, CacheDependency.class);
            if (cacheDependency != null) {
                // 获取方法上的 @Cacheable 缓存名称
                Cacheable cacheable = AnnotationUtils.findAnnotation(method, Cacheable.class);
                if (cacheable != null && cacheable.value().length > 0) {
                    String currentCache = cacheable.value()[0]; // 主缓存名称
                    // 注册dependents关系：当前缓存 → 依赖缓存
                    registry.registerDependency(currentCache, cacheDependency.value());
                }
            }
        }
        return bean;
    }
}
