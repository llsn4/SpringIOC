package com.framework.beans;

/**
 * @author 苏航
 * @description 用来封装Bean标签数据
 * id属性
 * class属性
 * property子标签的数据
 * @date 2023/4/14 13:00
 **/
public class BeanDefinition {
    private String id;
    private String className;
    private MutablePropertyValues propertyValues;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BeanDefinition() {
        propertyValues=new MutablePropertyValues();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public MutablePropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(MutablePropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}

