package com.umax.cache.event.rabbitmq;

import com.umax.cache.event.common.utils.InetAddressUtil;
import com.umax.cache.event.core.annotations.EnableCacheEvent;
import com.umax.cache.event.rabbitmq.bean.CacheBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

/**
 * @author wangyingbo
 * @since 2023-02-02 9:51
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitMqTests.class)
@EnableCacheEvent
@EnableCaching
@SpringBootApplication(scanBasePackages = "com.umax.cache.event")
public class RabbitMqTests {

    @Autowired
    CacheBean bean;

//    @Before
//    public void setup() {
//        System.setProperty("HOST_IP", "11.168.2.70");
//    }

    @Test
    public void contextLoads() {
        String propHostIp = System.getProperty("HOST_IP");
        System.out.println(propHostIp);
        System.out.println(InetAddressUtil.getHostIp());
        // 测试 Spring 上下文是否能正常加载
    }

    @Test
    public void test() throws InterruptedException {
        Stream.of(bean.list()).forEach(System.out::println);
        Stream.of(bean.list()).forEach(System.out::println);
        Stream.of(bean.list1()).forEach(System.out::println);
        Stream.of(bean.list1()).forEach(System.out::println);
        Stream.of(bean.list2()).forEach(System.out::println);
        Stream.of(bean.list2()).forEach(System.out::println);
        bean.clear();
        // mq有延迟
        Thread.sleep(2000);
        Stream.of(bean.list()).forEach(System.out::println);
        Stream.of(bean.list1()).forEach(System.out::println);
        Stream.of(bean.list2()).forEach(System.out::println);
    }
}
