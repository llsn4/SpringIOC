package com.framework.context.support;

import com.framework.beans.BeanDefinition;
import com.framework.beans.factory.support.BeanDefinitionReader;
import com.framework.beans.factory.support.BeanDefinitionRegistry;
import com.framework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 苏航
 * @description ApplicationContext接口的子实现类--用于立即加载
 * @date 2023/4/14 15:29
 **/
public abstract class AbstractApplicationContext implements ApplicationContext {
    /**
     * 声明解析器变量
     */
    protected BeanDefinitionReader beanDefinitionReader;
    //定义用于存储bean对象的map容器
    protected Map<String,Object> singletonObjects=new HashMap<>();
    /**
     * 声明记录配置文件类路径的变量
     */
    protected String configLocation;
    @Override
    public void refresh() throws Exception {
        //加载beanDefinition对象
        beanDefinitionReader.loadBeanDefinitions(configLocation);
        //初始化bean
        finishInitialization();

    }

    /**
     * bean对象的初始化
     * @throws Exception
     */
    private void finishInitialization()throws Exception{
        //获取注册表对象
        BeanDefinitionRegistry registry = beanDefinitionReader.getRegistry();
        //获取beanDefinition对象
        String[] beanDefinitionNames = registry.getBeanDefinitionNames();
        //进行bean的初始化
        for (String names : beanDefinitionNames) {
            BeanDefinition definition = registry.getBeanDefinition(names);
            getBean(names);
        }
    }
}
