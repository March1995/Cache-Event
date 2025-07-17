package com.umax.cache.event.core.aop;

import com.umax.cache.event.core.properties.EventCacheProperties;
import com.umax.cache.event.core.push.EventCachePushService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.scheduling.annotation.Async;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author wangyingbo
 * @since 2023-01-30 19:47
 **/
@Aspect
public class EventCacheEvictAop implements ApplicationContextAware {

    public static final Logger log = LoggerFactory.getLogger(EventCacheEvictAop.class);

    @Resource
    private EventCacheProperties eventCacheProperties;

    private ApplicationContext applicationContext;

//    @Pointcut("@annotation(com.umax.cache.event.common.annotations.EventCacheEvict)")
//    public void catchAll() {
//    }

    @Pointcut("@annotation(org.springframework.cache.annotation.CacheEvict)")
    public void catchAll1() {
    }

    // todo after是否合理

    /**
     * @param joinPoint
     * @return
     */
    @After("catchAll1()")
    @Async
    public void doAfter(JoinPoint joinPoint) {
        List<String> cacheNameList = new ArrayList<>();
//        EventCacheEvict eventCacheEvict = AnnotationUtils.getAnnotation(((MethodSignature) joinPoint.getSignature()).getMethod(), EventCacheEvict.class);
//        if (Objects.nonNull(eventCacheEvict)) {
//            cacheNameList.addAll(List.of(eventCacheEvict.eventNames()));
//        }
        CacheEvict cacheEvict = AnnotationUtils.getAnnotation(((MethodSignature) joinPoint.getSignature()).getMethod(), CacheEvict.class);
        if (Objects.nonNull(cacheEvict)) {
            cacheNameList.addAll(List.of(cacheEvict.value()));
        }

        cacheNameList.forEach(eventName -> {
            log.info("事件[{}]发布,推送方式[{}]", eventName, eventCacheProperties.getPushType());
            switch (eventCacheProperties.getPushType()) {
                case RABBIT_MQ: {
                    EventCachePushService rabbitmqPushService = applicationContext.getBean("RabbitmqPushService", EventCachePushService.class);
                    rabbitmqPushService.push(eventName);
                    break;
                }
                default: {
                    EventCachePushService springEventPushService = applicationContext.getBean("SpringEventPushService", EventCachePushService.class);
                    springEventPushService.push(eventName);
                    break;
                }
            }
        });
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
