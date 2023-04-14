package com.framework.context.support;

import com.framework.beans.BeanDefinition;
import com.framework.beans.MutablePropertyValues;
import com.framework.beans.PropertyValue;
import com.framework.beans.factory.support.BeanDefinitionRegistry;
import com.framework.beans.factory.xml.XMLBeanDefinitionReader;
import com.framework.utils.StringUtils;

import java.lang.reflect.Method;

/**
 * @author 苏航
 * @description IOC容器的具体子实现类 --用于加载类路径下xml格式的配置文件
 * @date 2023/4/14 15:41
 **/
public class ClassPathXmlApplicationContext extends AbstractApplicationContext{


    public ClassPathXmlApplicationContext(String configLocation) {
        this.configLocation=configLocation;
        //构建解析器对象
        beanDefinitionReader=new XMLBeanDefinitionReader();
        //调用refresh
        try {
            this.refresh();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据bean对象的名称获取对象
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Object getBean(String name) throws Exception {
        //判断对象容器中是否包含指定名称的对象，如果不包含，需要自行创建
        Object obj = singletonObjects.get(name);
        if(obj!=null){
            return obj;
        }
        //获取beanDefinition对象
        BeanDefinitionRegistry registry = beanDefinitionReader.getRegistry();
        BeanDefinition definition = registry.getBeanDefinition(name);
        //获取bean信息中的className--全类名
        String className = definition.getClassName();
        //通过反射创建对象
        Class<?> clazz = Class.forName(className);
        Object beanObj = clazz.newInstance();
        //进行依赖注入操作
        MutablePropertyValues propertyValues = definition.getPropertyValues();
        for (PropertyValue propertyValue : propertyValues) {
            //获取name属性
            String propertyName = propertyValue.getName();
            //获取ref属性
            String ref = propertyValue.getRef();
            //获取value
            String value= propertyValue.getValue();
            if(ref!=null&& !"".equals(ref)){
                //获取依赖的bean对象
                Object bean = getBean(ref);
                //拼接set方法
                String method = StringUtils.getSetterMethodByFieldName(propertyName);
                Method[] methods = clazz.getMethods();
                for (Method method1 : methods) {
                    if(method1.getName().equals(method)){
                        method1.invoke(beanObj,bean);
                    }
                }
            }
            if(value!=null&&!"".equals(value)){
                String method = StringUtils.getSetterMethodByFieldName(propertyName);
                Method[] methods = clazz.getMethods();
                for (Method method1 : methods) {
                    if(method1.getName().equals(method)){
                        method1.invoke(beanObj,value);
                    }
                }
            }
        }
        //在返回beanObj之前，将该对象存到map容器中
        singletonObjects.put(name,beanObj);


        return beanObj;
    }

    @Override
    public <T> T getBean(String name, Class<? extends T> clazz) throws Exception {
        Object bean = getBean(name);
        if(bean==null){
            return null;
        }
      return clazz.cast(bean);
    }

    @Override
    public void refresh() throws Exception {
        super.refresh();
    }
}
