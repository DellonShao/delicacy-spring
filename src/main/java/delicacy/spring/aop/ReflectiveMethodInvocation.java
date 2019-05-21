package delicacy.spring.aop;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
*@Author: shaodilong
*@Description: 封装被代理的对象方法
*@Date: Created in 2019/5/20 14:20
*@Modify By:
*/
public class ReflectiveMethodInvocation implements MethodInvocation {
    //原始对象
    protected Object target;
    protected Method method;
    protected Object[] arguments;

    public ReflectiveMethodInvocation(Object target, Method method, Object[] arguments) {
        this.target = target;
        this.method = method;
        this.arguments = arguments;
    }

    @Override
    public Method getMethod() {
        return this.method;
    }

    @Override
    public Object[] getArguments() {
        return this.arguments;
    }

    /**
     * 调用被拦截对象的方法
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object proceed() throws Throwable {
        //这里是调用原始对象的方法，不支持拦截器链
        //为了支持拦截器链，可以做出以下修改：
        //将 proceed() 方法修改为调用代理对象的方法 method.invoke(proxy,args)。
        //在代理对象的 InvocationHandler 的 invoke 函数中，查看拦截器列表，如果有拦截器，则调用第一个拦截器并
        //返回，否则调用原始对象的方法。\
        //TODO:

        return this.method.invoke(target,arguments);
    }

    @Override
    public Object getThis() {
        return this.target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return this.method;
    }
}
