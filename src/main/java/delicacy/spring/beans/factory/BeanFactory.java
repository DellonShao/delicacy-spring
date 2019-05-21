package delicacy.spring.beans.factory;
/**
*@Author: shaodilong
*@Description: IOC容器顶层接口，在容器获取bean定义下，解决bean装配和获取的问题
*@Date: Created in 2019/5/7 20:03
*@Modify By:
*/
public interface BeanFactory {
    //根据bean的名字，获取IOC容器中的bean实例
    Object getBean(String name) throws IllegalAccessException, InstantiationException;
}
