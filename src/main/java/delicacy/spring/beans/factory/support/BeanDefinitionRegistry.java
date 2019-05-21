package delicacy.spring.beans.factory.support;

import delicacy.spring.beans.BeanDefinition;

/**
*@Author: shaodilong
*@Description:
*@Date: Created in 2019/5/7 17:59
*@Modify By:
*/
public interface BeanDefinitionRegistry {
    //注册beanDefinition
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
    //删除beanDefinition
    void removeBeanDefinition(String beanName);
    //获取beanDefinition
    BeanDefinition getBeanDefinition(String beanName);
    //判断是否存在这个beanDefinition
    boolean containsBeanDefinition(String beanName);
    //获取所有注册的bean名
    String[] getBeanDefinitionNames();
    //获取注册的beanDefinition个数
    int getBeanDefinitionCount();
    //判断这个bean名是否已经被使用了
    boolean isBeanNameInUse(String beanName);
}
