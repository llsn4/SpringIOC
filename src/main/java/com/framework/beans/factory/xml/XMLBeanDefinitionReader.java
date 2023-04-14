package com.framework.beans.factory.xml;

import com.framework.beans.BeanDefinition;
import com.framework.beans.MutablePropertyValues;
import com.framework.beans.PropertyValue;
import com.framework.beans.factory.support.BeanDefinitionReader;
import com.framework.beans.factory.support.BeanDefinitionRegistry;
import com.framework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * @author 苏航
 * @description 针对XML配置文件进行解析的类---实现功能
 * @date 2023/4/14 14:42
 **/
public class XMLBeanDefinitionReader implements BeanDefinitionReader {

    /**
     * 声明注册表对象
     */
    private BeanDefinitionRegistry registry;

    public XMLBeanDefinitionReader() {
       registry=new SimpleBeanDefinitionRegistry();
    }

    /**
     * 获取注册表对象
     *
     * @return
     */
    @Override
    public BeanDefinitionRegistry getRegistry() {
        return  registry;
    }

    /**
     * 加载配置文件并在注册表中注册
     *
     * @param configuration
     */
    @Override
    public void loadBeanDefinitions(String configuration) throws DocumentException {
        //使用dom4j进行xml配置文件的解析
        SAXReader reader=new SAXReader();
        //获取字节输入流,获取类路径下的配置文件
        InputStream resourceAsStream =
                XMLBeanDefinitionReader.class.getClassLoader().getResourceAsStream(configuration);
        Document document = reader.read(resourceAsStream);
        //根据Document对象获取根标签对象(beans)
        Element rootElement = document.getRootElement();
        //获取根标签下所有的bean标签对象
        List<Element> beanElements = rootElement.elements("bean");
        //遍历集合
        for (Element beanElement : beanElements) {
            //获取id属性
            String id = beanElement.attributeValue("id");
            //获取class属性
            String className = beanElement.attributeValue("class");
            //将id和class属性封装到BeanDefinition对象中
            //创建BeanDefinition对象
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setId(id);
            beanDefinition.setClassName(className);
            //获取bean标签下所有的property标签对象
            List<Element> propertyElements = beanElement.elements("property");
            //创建MutablePropertyValues对象
            MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
            for (Element propertyElement : propertyElements) {
                //name属性
                String name = propertyElement.attributeValue("name");
                //ref属性
                String ref = propertyElement.attributeValue("ref");
                //value属性
                String value = propertyElement.attributeValue("value");
                PropertyValue propertyValue = new PropertyValue(name, ref, value);
                mutablePropertyValues.addPropertyValue(propertyValue);
            }
            //将MutablePropertyValues对象封装到beanDefinition中
            beanDefinition.setPropertyValues(mutablePropertyValues);
            //将beanDefinition对象注册到注册表中
            registry.registerBeanDefinition(id,beanDefinition);

        }

    }
}
