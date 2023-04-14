package com.framework.beans.factory;

/**
 * @author 苏航
 * @description IOC容器的父接口
 * @date 2023/4/14 15:18
 **/
public interface BeanFactory {
   public Object getBean(String name)throws Exception;
    public <T>T getBean(String name,Class<? extends T> clazz)throws Exception;
}
