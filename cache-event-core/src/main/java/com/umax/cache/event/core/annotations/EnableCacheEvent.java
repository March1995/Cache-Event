package com.umax.cache.event.core.annotations;

import com.umax.cache.event.core.aop.EventCacheEvictAop;
import com.umax.cache.event.core.registry.EventRegistry;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author wangyingbo
 * @since 2023-01-30 17:37
 **/
@Import({EventRegistry.class, EventCacheEvictAop.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableCacheEvent {
}
