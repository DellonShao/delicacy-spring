package delicacy.spring.beans;
/**
*@Author: shaodilong
*@Description: Bean的后置处理器
*@Date: Created in 2019/5/8 21:06
*@Modify By:
*/
public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(Object bean, String beanName);
    Object postProcessAfterInitialization(Object bean, String beanName) throws IllegalAccessException, InstantiationException;
}
