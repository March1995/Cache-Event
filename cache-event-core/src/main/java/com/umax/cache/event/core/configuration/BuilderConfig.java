package com.umax.cache.event.core.configuration;

import com.umax.cache.event.core.event.CacheClearEventClientBuilder;
import com.umax.cache.event.core.event.spring.CacheClearEventSpingEventClientBuilder;
import com.umax.cache.event.core.listen.CacheClearEventListenerBuilder;
import com.umax.cache.event.core.listen.spring.CacheClearSpringListenerBuilder;
import com.umax.cache.event.core.push.EventCachePushService;
import com.umax.cache.event.core.push.SpringEventPushServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

@Configuration
public class BuilderConfig {

    @Bean
    @ConditionalOnMissingBean(value = {CacheClearEventListenerBuilder.class})
    public CacheClearEventListenerBuilder cacheClearEventListenerBuilder(GenericApplicationContext applicationContext) {
        return new CacheClearSpringListenerBuilder(applicationContext);
    }

    @Bean
    @ConditionalOnMissingBean(value = {CacheClearEventClientBuilder.class})
    public CacheClearEventClientBuilder cacheClearEventBuilder() {
        return new CacheClearEventSpingEventClientBuilder();
    }

    @Bean("SpringEventPushService")
    @ConditionalOnMissingBean(value = {EventCachePushService.class})
    public EventCachePushService eventCachePushService() {
        return new SpringEventPushServiceImpl();
    }

//    @Bean
//    @ConditionalOnProperty(name = EventCacheProperties.PREFIX, havingValue = "SPRING_EVENT")
//    public CacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
//        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofSeconds(600))  //存入Redis的时间设置600秒
//                //.entryTtl(Duration.ofDays(1))
//                .disableCachingNullValues();
//        return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(cacheConfiguration).build();
//    }


}
