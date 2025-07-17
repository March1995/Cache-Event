package com.umax.cache.event.rabbitmq.push;

import com.umax.cache.event.core.push.EventCachePushService;
import com.umax.cache.event.rabbitmq.constant.RabbitConstants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import javax.annotation.Resource;

/**
 * @author wangyingbo
 * @since  2023-02-03 16:45
 **/
public class RabbitmqPushServiceImpl implements EventCachePushService {

    @Resource
    RabbitTemplate rabbitTemplate;

    @Override
    public void push(String eventName) {
        rabbitTemplate.convertAndSend(RabbitConstants.EXCHANGE_PRE, "", eventName);
    }
}
