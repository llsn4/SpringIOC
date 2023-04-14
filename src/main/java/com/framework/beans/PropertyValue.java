package com.framework.beans;

/**
 * @author 苏航
 * @description 用来封装bean标签下的property标签的属性
 * name
 * ref
 * value 给基本数据类型和字符串类型赋值
 * @date 2023/4/14 12:37
 **/
public class PropertyValue {

    private String name;
    private String ref;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public PropertyValue(String name, String ref, String value) {
        this.name = name;
        this.ref = ref;
        this.value = value;
    }

    public PropertyValue() {
    }

    private String value;


}
