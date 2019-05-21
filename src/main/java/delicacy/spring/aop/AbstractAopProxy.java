package delicacy.spring.aop;
/**
*@Author: shaodilong
*@Description: 继承AopProxy接口，有获取代理对象能力，继承这个类的子类拥有AdvisedSupport的支持
*@Date: Created in 2019/5/20 15:07
*@Modify By:
*/
public abstract class AbstractAopProxy implements AopProxy{
    protected AdvisedSupport advised;

    public AbstractAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }
}
