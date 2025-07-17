package com.umax.cache.event.core.listen.spring;

import com.umax.cache.event.core.listen.CacheClearEventListenerBuilder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 创建监听器
 *
 * @author wangyingbo
 * @since 2023-01-30 17:56
 **/
public class CacheClearSpringListenerBuilder implements CacheClearEventListenerBuilder {

    private GenericApplicationContext applicationContext;

    /**
     * 这里选择手动去添加bean
     *
     * @throws BeansException
     */
    @Override
    public void buildListener() {
//        ConstructorArgumentValues cargs = new ConstructorArgumentValues();
//        cargs.addIndexedArgumentValue(0, eventCacheList);
//        cargs.addIndexedArgumentValue(1, applicationContext.getBean(CacheManager.class));
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(CacheClearSpringListener.class);
        applicationContext.registerBeanDefinition(CacheClearSpringListener.class.getSimpleName(), rootBeanDefinition);
        // 需要getBean后才是真实的创建实例
        applicationContext.getBean(CacheClearSpringListener.class);
    }


    public CacheClearSpringListenerBuilder(GenericApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

}
