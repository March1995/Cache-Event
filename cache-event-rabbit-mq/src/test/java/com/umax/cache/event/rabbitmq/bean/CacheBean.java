package com.umax.cache.event.rabbitmq.bean;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangyingbo
 * @since  2023-01-30 18:41
 **/
@Service
public class CacheBean {

    @CacheEvict(value = {"clear_dept_list", "clear_dept_list_1"})
    public void clear() {

    }

//    @EventCacheEvict(eventNames = {"clear_dept_list_1",}, value = {"dept_list_1"})
//    public void clear1() {
//
//    }

    @Cacheable(value = {"dept_list", "dept_list2"})
    public List<String> list() {
        System.out.print("非缓存操作");
        return List.of("1", "2");
    }

    @Cacheable(value = {"dept_by_id"}, key = "#id")
    public String getById(String id) {
        System.out.print("非缓存操作");
        return id;
    }

    //    @EventCacheable(cacheNames = "dept_list_1", listenEventNames = {"clear_dept_list"})
    @Cacheable(cacheNames = "clear_dept_list_1")
    public List<String> list1() {
        System.out.print("非缓存操作");
        return List.of("3", "4");
    }

    @Cacheable(cacheNames = "dept_list_1")
    public List<String> list2() {
        System.out.print("非缓存操作");
        return List.of("5", "6");
    }
}
