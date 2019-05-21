package delicacy.spring;

import delicacy.spring.beans.BeanPostProcessor;

/**
*@Author: shaodilong
*@Description:
*@Date: Created in 2019/5/21 16:38
*@Modify By:
*/
public class Logger implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("Initialize bean " + beanName + " start!");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws IllegalAccessException, InstantiationException {
        System.out.println("Initialize bean " + beanName + " end!");
        return bean;
    }
}
