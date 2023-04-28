package com.umax.cache.event.core.registry;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;

/**
 * @author wangyingbo
 * @date 2023-01-30 17:13
 **/
@Order(Integer.MIN_VALUE)
public class WebCacheEventRegistry extends EventRegistry implements CommandLineRunner {

    @Override
    public void run(String... args) {
        start();
    }

}