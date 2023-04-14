package com.framework.beans.factory.support;

import com.framework.beans.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 苏航
 * @description 注册表接口的子实现类
 * @date 2023/4/14 14:32
 **/
public class SimpleBeanDefinitionRegistry implements BeanDefinitionRegistry{
    /**
     * 定义一个容器，用来存储BeanDefinition对象
     */
    private Map<String ,BeanDefinition> beanDefinitionMap=new HashMap<>();
    /**
     * 注册beanDefinition对象到注册表中
     *
     * @param beanName
     * @param beanDefinition
     */
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName,beanDefinition);

    }

    /**
     * 从注册表中删除指定的beanDefinition
     *
     * @param beanName
     */
    @Override
    public void removeBeanDefinition(String beanName) {
        beanDefinitionMap.remove(beanName);
    }

    /**
     * 从注册表中获取name对应的beanDefinition
     *
     * @param beanName
     * @return
     */
    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return beanDefinitionMap.get(beanName);
    }

    /**
     * 检查注册表中是否有name对应的beanDefinition
     *
     * @param beanName
     * @return
     */
    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    /**
     * 获取BeanDefinition对象的个数
     *
     * @return
     */
    @Override
    public int getBeanDefinitionCount() {
        return beanDefinitionMap.size();
    }

    /**
     * 获取所有BeanDefinition的name
     *
     * @return
     */
    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }
}
