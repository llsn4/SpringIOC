package com.framework.beans.factory.support;

import com.framework.beans.BeanDefinition;

/**
 * @author 苏航
 * @description 注册表对象
 * @date 2023/4/14 14:27
 **/
public interface BeanDefinitionRegistry {

    /**
     * 注册beanDefinition对象到注册表中
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName,BeanDefinition beanDefinition);

    /**
     * 从注册表中删除指定的beanDefinition
     * @param beanName
     */
    void removeBeanDefinition(String beanName);

    /**
     * 从注册表中获取name对应的beanDefinition
     * @param beanName
     * @return
     */
    BeanDefinition getBeanDefinition(String beanName);

    /**
     * 检查注册表中是否有name对应的beanDefinition
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 获取BeanDefinition对象的个数
     * @return
     */
    int getBeanDefinitionCount();

    /**
     * 获取所有BeanDefinition的name
     * @return
     */
    String[] getBeanDefinitionNames();
}
