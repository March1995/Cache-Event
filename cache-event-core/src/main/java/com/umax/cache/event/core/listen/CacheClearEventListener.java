package com.umax.cache.event.core.listen;

/**
 * @author wangyingbo
 * @since  2023-02-02 17:13
 **/
public interface CacheClearEventListener {

    /**
     * 把监听事件加入listener 清除时需要
     *
     * @param
     */
//    public void addEventCacheable(List<EventCacheable> eventCacheList);

    void clear(String cacheName);

    void evict(String cacheName, String key);
}
