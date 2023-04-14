package com.framework.beans.factory.support;

import com.framework.beans.BeanDefinition;
import org.dom4j.DocumentException;

/**
 * @author 苏航
 * @description 用来解析配置文件的接口  ---只定义规范
 * @date 2023/4/14 14:39
 **/
public interface BeanDefinitionReader {
    /**
     * 获取注册表对象
     * @return
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * 加载配置文件并在注册表中注册
     * @param configuration
     */
    void loadBeanDefinitions(String configuration) throws DocumentException;

}
