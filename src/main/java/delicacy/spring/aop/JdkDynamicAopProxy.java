package delicacy.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
*@Author: shaodilong
*@Description: 一个基于JDK的动态代理，只能针对实现了接口的动态代理
*@Date: Created in 2019/5/20 15:13
*@Modify By:
*/
public class JdkDynamicAopProxy extends AbstractAopProxy implements InvocationHandler {
    public JdkDynamicAopProxy(AdvisedSupport advised) {
        super(advised);
    }

    /**
     * 获取代理对象
     *
     * @return
     */
    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),advised.getTargetSource().getInterfaces(),this);
    }

    /**
     * 控制访问
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodInterceptor methodInterceptor = this.advised.getMethodInterceptor();
        //如果方法匹配器存在且该对象的方法匹配成功，则调用用户提供的方法拦截器的invoke方法
        if(advised.getMethodMatcher() != null && advised.getMethodMatcher().matches(method, advised.getTargetSource().getTarget().getClass())){
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(),method,args));
        }else {
            //否则就调用原对象的方法
            return method.invoke(advised.getTargetSource().getTarget(),args);
        }
    }
}
