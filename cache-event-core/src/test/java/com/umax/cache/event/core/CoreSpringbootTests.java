package com.umax.cache.event.core;

import com.umax.cache.event.core.annotations.EnableWebCacheEvent;
import com.umax.cache.event.core.bean.CacheBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

/**
 * @author wangyingbo
 * @date 2023-02-02 9:51
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreSpringbootTests.class)
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {CoreSpringbootTests.class})
@ComponentScan(basePackages = "com.umax.cache.event")
@EnableWebCacheEvent
@EnableAspectJAutoProxy
@EnableCaching
@EnableAutoConfiguration
public class CoreSpringbootTests {

    @Autowired
    CacheBean bean;

    @Test
    public void test() throws InterruptedException {
//        Stream.of(bean.list()).forEach(System.out::println);
//        Stream.of(bean.list()).forEach(System.out::println);
//        Stream.of(bean.list1()).forEach(System.out::println);
//        Stream.of(bean.list1()).forEach(System.out::println);
//        Stream.of(bean.list2()).forEach(System.out::println);
//        Stream.of(bean.list2()).forEach(System.out::println);
        Stream.of(bean.getById("1")).forEach(System.out::println);
        Stream.of(bean.getById("2")).forEach(System.out::println);
        Stream.of(bean.getById("1")).forEach(System.out::println);
        bean.clear(1);
//        Stream.of(bean.list()).forEach(System.out::println);
//        Stream.of(bean.list1()).forEach(System.out::println);
//        Stream.of(bean.list2()).forEach(System.out::println);
    }
}
