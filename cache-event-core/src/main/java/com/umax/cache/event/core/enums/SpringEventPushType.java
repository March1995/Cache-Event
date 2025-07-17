package com.umax.cache.event.core.enums;

/**
 * @author wangyingbo
 * @since 2023-02-02 16:27
 **/
public enum SpringEventPushType {

    SPRING_EVENT("SPRING_EVENT"),


    RABBIT_MQ("RABBIT_MQ");


    private String name;

    SpringEventPushType(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
