package com.umax.cache.event.core.push;

import com.umax.cache.event.core.event.CacheClearEvent;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;

import javax.annotation.Resource;

/**
 * @author wangyingbo
 * @date 2025-07-10 09:59
 */
//@Component(value = "SpringEventPushService")
public class SpringEventPushServiceImpl implements EventCachePushService, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Resource
    private ApplicationEventPublisher eventPublisher;

    @Override
    public void push(String eventName) {
//        Object bean = applicationContext.getBean(CacheClearSpringListener.class);
//        applicationContext.publishEvent(bean);
        eventPublisher.publishEvent(new CacheClearEvent(this, eventName));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
