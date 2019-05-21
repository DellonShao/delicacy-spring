package delicacy.spring.aop;
/**
*@Author: shaodilong
*@Description: 封装被代理对象的相关信息
*@Date: Created in 2019/5/20 14:16
*@Modify By:
*/
public class TargetSource {
    //原始对象
    private Object target;
    private Class<?> targetClass;
    private Class<?>[] interfaces;

    public TargetSource(Object target, Class<?> targetClass, Class<?>... interfaces) {
        this.target = target;
        this.targetClass = targetClass;
        this.interfaces = interfaces;
    }

    public Object getTarget() {
        return target;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Class<?>[] getInterfaces() {
        return interfaces;
    }
}
