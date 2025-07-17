package com.umax.cache.event.core.registry;

import com.umax.cache.event.core.event.CacheClearEventBuilder;
import com.umax.cache.event.core.listen.CacheClearEventListenerBuilder;
import com.umax.cache.event.core.properties.EventCacheProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.Resource;

/**
 * @author wangyingbo
 * @since 2023-02-03 22:08
 **/
@EnableConfigurationProperties(EventCacheProperties.class)
public class EventRegistry implements InitializingBean {

    @Resource
    private CacheClearEventListenerBuilder listenerBuilder;
    @Resource
    private CacheClearEventBuilder clearEventBuilder;

    private void start() {
        // 因为rabbitmq 注册队列需要用到listener 所以顺序在前
        buildListener();
        regisClient();
    }

    private void regisClient() {
        clearEventBuilder.build();
    }

    private void buildListener() {
        listenerBuilder.buildListener();
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        start();
    }
}
