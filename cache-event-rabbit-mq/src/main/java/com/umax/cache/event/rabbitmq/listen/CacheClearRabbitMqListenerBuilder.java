package com.umax.cache.event.rabbitmq.listen;

import com.umax.cache.event.core.listen.CacheClearEventListenerBuilder;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author wangyingbo
 * @since 2023-02-02 15:04
 **/
public class CacheClearRabbitMqListenerBuilder implements CacheClearEventListenerBuilder {

    private final GenericApplicationContext applicationContext;
    private final ConnectionFactory connectionFactory;
    private final CacheClearRabbitMqListener listener;

    public CacheClearRabbitMqListenerBuilder(GenericApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.connectionFactory = applicationContext.getBean(ConnectionFactory.class);
        this.listener = applicationContext.getBean(CacheClearRabbitMqListener.class);
    }

    @Override
    public void buildListener() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        container.setMaxConcurrentConsumers(10);
        container.setConcurrentConsumers(5);
        container.setPrefetchCount(1);
        //监听处理类
        container.setMessageListener(listener);
        applicationContext.getBeanFactory().registerSingleton(SimpleMessageListenerContainer.class.getSimpleName(), container);
    }

}
