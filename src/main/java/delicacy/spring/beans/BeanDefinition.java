package delicacy.spring.beans;

import java.util.Properties;

/**
*@Author: shaodilong
*@Description: bean的定义
*@Date: Created in 2019/5/7 16:20
*@Modify By:
*/

//Object1 ob1 = new Object1(true,1,"2");
//类型名--------------------属性键值对
public class BeanDefinition {
    private Object bean;
    //bean的类型
    private Class beanClass;
    //bean的名字
    private String beanClassName;
    //bean中的属性键值对
    private PropertyValues propertyValues = new PropertyValues();

    public BeanDefinition() {
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
        try {
            this.beanClass = Class.forName(beanClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
