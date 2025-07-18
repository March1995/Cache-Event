package com.umax.cache.event.rabbitmq.config;

import com.umax.cache.event.core.event.CacheClearEventClientBuilder;
import com.umax.cache.event.core.push.EventCachePushService;
import com.umax.cache.event.rabbitmq.event.CacheClearEventRabbitMqClientBuilder;
import com.umax.cache.event.rabbitmq.listen.CacheClearRabbitMqListenerBuilder;
import com.umax.cache.event.rabbitmq.push.RabbitmqPushServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author wangyingbo
 * @date 2025-07-16 19:44
 */
@Configuration
@ConditionalOnProperty(name = "com.umax.cache-event.push-type", havingValue = "RABBIT_MQ")
public class RabbitMqBuilderConfig {

    @Bean
    public CacheClearRabbitMqListenerBuilder cacheClearRabbitMqListenerBuilder(GenericApplicationContext applicationContext) {
        return new CacheClearRabbitMqListenerBuilder(applicationContext);
    }

    @Bean
    public CacheClearEventClientBuilder cacheClearEventBuilder(GenericApplicationContext applicationContext) {
        return new CacheClearEventRabbitMqClientBuilder(applicationContext);
    }

    @Bean(name = "RabbitmqPushService")
    public EventCachePushService eventCachePushService() {
        return new RabbitmqPushServiceImpl();
    }
}
