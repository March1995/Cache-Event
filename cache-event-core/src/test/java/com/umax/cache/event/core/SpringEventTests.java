package com.umax.cache.event.core;

import com.umax.cache.event.core.annotations.EnableCacheEvent;
import com.umax.cache.event.core.bean.CacheBean;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

/**
 * @author wangyingbo
 * @since 2023-01-30 18:39
 **/

@EnableCacheEvent
@EnableAspectJAutoProxy
@EnableCaching
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringEventTests.class)
@SpringBootApplication(scanBasePackages = "com.umax.cache.event")
public class SpringEventTests {
    public static void main(String[] args) {
//        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringEventTests.class);

        SpringApplication app = new SpringApplication(SpringEventTests.class);
        app.setMainApplicationClass(SpringEventTests.class);
        ConfigurableApplicationContext ctx = app.run(args);

        CacheBean bean = ctx.getBean(CacheBean.class);
//        Stream.of(bean.list()).forEach(System.out::println);
//        Stream.of(bean.list()).forEach(System.out::println);
//        Stream.of(bean.list1()).forEach(System.out::println);
//        Stream.of(bean.list1()).forEach(System.out::println);
        Stream.of(bean.getById("1")).forEach(System.out::println);
//        Stream.of(bean.getById("1")).forEach(System.out::println);
        bean.clear(1);
//        Stream.of(bean.list()).forEach(System.out::println);
//        Stream.of(bean.list1()).forEach(System.out::println);
//        Stream.of(bean.getById("1")).forEach(System.out::println);
//        Stream.of(bean.getById("1")).forEach(System.out::println);
    }

}
