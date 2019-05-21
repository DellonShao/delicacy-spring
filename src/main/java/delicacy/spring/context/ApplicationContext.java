package delicacy.spring.context;

import delicacy.spring.beans.factory.BeanFactory;

/**
*@Author: shaodilong
*@Description: 
*@Date: Created in 2019/5/8 10:51
*@Modify By:
*/

//要实现一个 IoC 容器时，需要先通过 ResourceLoader 获取一个 Resource，其中包括了容器的配置、Bean
//的定义信息。接着，使用 BeanDefinitionReader接口暴露的方法读取并注册该 Resource 中的 BeanDefinition 信息。最后，
//把 BeanDefinition 保存在 BeanFactory 中，容器配置完毕可以使用。
public interface ApplicationContext extends BeanFactory {
}
