package com.framework.context;

import com.framework.beans.factory.BeanFactory;

/**
 * @author 苏航
 * @description 定义非延时加载功能
 * @date 2023/4/14 15:26
 **/
public interface ApplicationContext  extends BeanFactory {
    void refresh() throws Exception;
}
