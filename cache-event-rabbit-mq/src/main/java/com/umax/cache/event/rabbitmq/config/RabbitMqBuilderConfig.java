package com.umax.cache.event.rabbitmq.config;

import com.umax.cache.event.core.event.CacheClearEventBuilder;
import com.umax.cache.event.core.event.spring.CacheClearEventSpingEventBuilder;
import com.umax.cache.event.rabbitmq.listen.CacheClearRabbitMqListenerBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author wangyingbo
 * @date 2025-07-16 19:44
 */
@Configuration
public class RabbitMqBuilderConfig {

    @Bean
    @ConditionalOnProperty(name = "com.umax.cache-event.push-type", havingValue = "RABBIT_MQ")
    public CacheClearRabbitMqListenerBuilder cacheClearRabbitMqListenerBuilder(GenericApplicationContext applicationContext) {
        return new CacheClearRabbitMqListenerBuilder(applicationContext);
    }

    @Bean
    @ConditionalOnProperty(name = "com.umax.cache-event.push-type", havingValue = "RABBIT_MQ")
    public CacheClearEventBuilder cacheClearEventBuilder() {
        return new CacheClearEventSpingEventBuilder();
    }
}
