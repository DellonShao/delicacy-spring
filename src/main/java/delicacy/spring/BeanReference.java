package delicacy.spring;
/**
*@Author: shaodilong
*@Description: 用于表示property标签的ref属性里的对象
*@Date: Created in 2019/5/7 21:57
*@Modify By:
*/
public class BeanReference {
    private String name;
    private Object bean;

    public BeanReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
