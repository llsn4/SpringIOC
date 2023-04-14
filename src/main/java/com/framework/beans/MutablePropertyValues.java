package com.framework.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author 苏航
 * @description 用来存储和管理多个PropertyValue对象
 * @date 2023/4/14 12:41
 **/
public class MutablePropertyValues  implements Iterable<PropertyValue>{
    //定义list对象，用来存储PropertyValue对象
    private final List<PropertyValue> propertyValueList;

    public MutablePropertyValues(List<PropertyValue> propertyValueList) {
        if(propertyValueList==null){
            this.propertyValueList=new ArrayList<PropertyValue>();
        }
        else {
            this.propertyValueList = propertyValueList;
        }

    }

    public MutablePropertyValues() {
        this.propertyValueList=new ArrayList<PropertyValue>();
    }
    /**
     * 获取所有PropertyValue对象，以数组形式返回
     * @return
     */
    public PropertyValue[] getPropertyValues(){
        //将集合转换数组，然后返回
       return   propertyValueList.toArray(new PropertyValue[0]);

    }
    /**
     * 判断集合是否为空
     * @return
     */
    public boolean isEmpty(){
       return propertyValueList.isEmpty();
    }
    /**
     * 根据name属性获取PropertyValue对象
     * @param propertyName
     * @return
     */
    public PropertyValue getPropertyValue(String propertyName){
        //遍历集合对象
        for (PropertyValue propertyValue : propertyValueList) {
            if(propertyValue.getName().equals(propertyName)){
                return propertyValue;
            }
        }
        return null;
    }
    /**
     * 向集合中添加propertyValue对象
     * @param propertyValue
     * @return
     */
    public MutablePropertyValues addPropertyValue(PropertyValue propertyValue){
        //判断集合中存储的propertyValue对象是否和传递进来的重复了，如果重复了就覆盖
        for (int i = 0; i < propertyValueList.size(); i++) {
            //获取集合每一个propertyValue对象
            PropertyValue currentPropertyValue = propertyValueList.get(i);
            if(currentPropertyValue.getName().equals(propertyValue.getName())){
                //需要覆盖
                propertyValueList.set(i,propertyValue);
                //为了实现链式编程
                return this;
            }
        }
        //需要增加
        propertyValueList.add(propertyValue);
        //为了实现链式编程
        return this;

    }
    //判断是否有指定name值的对象
    public boolean contains(String propertyName){
        return getPropertyValue(propertyName)!=null;
    }

    /**
     * 获取迭代器对象
     * @return
     */
    @Override
    public Iterator<PropertyValue> iterator() {
        return propertyValueList.iterator();
    }
}
