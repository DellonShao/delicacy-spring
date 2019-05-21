package delicacy.spring.beans.factory;

import delicacy.spring.BeanReference;
import delicacy.spring.aop.BeanFactoryAware;
import delicacy.spring.beans.BeanDefinition;
import delicacy.spring.beans.PropertyValue;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
*@Author: shaodilong
*@Description: 实现自动装配的BeanFactory
*@Date: Created in 2019/5/7 21:47
*@Modify By:
*/
public class AutowireCapableBeanFactory extends AbstractBeanFactory{
    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws InstantiationException, IllegalAccessException {
        if(bean instanceof BeanFactoryAware){
            ((BeanFactoryAware)bean).setBeanFactory(this);
        }
        for(PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()){
            Object value = propertyValue.getValue();
            //如果属性是ref而不是value类型就先实例化那个ref的bean，然后装载到这个value里
            if(value instanceof BeanReference){
                BeanReference beanReference = (BeanReference)value;
                if(beanReference.getBean() == null) {
                    value = getBean(beanReference.getName());
                }
            }

            String propertyName = propertyValue.getName();
            try {
                //通过setter方法注入
                Class paramType = bean.getClass().getDeclaredField(propertyName).getType();
                Method declareMethod = bean.getClass().getDeclaredMethod("set"+propertyName.substring(0,1).toUpperCase()+propertyName.substring(1),paramType);
                declareMethod.setAccessible(true);
                declareMethod.invoke(bean,value);
            } catch (NoSuchMethodException e) {
                //如果没有setter方法，则直接为属性赋值
                try {
                    Field declaredField = bean.getClass().getDeclaredField(propertyName);
                    declaredField.setAccessible(true);
                    declaredField.set(bean,value);
                } catch (NoSuchFieldException e1) {
                    e1.printStackTrace();
                }
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }
}
