package delicacy.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
*@Author: shaodilong
*@Description: 封装了TargetSource,MethodInterceptor, MethodMatcher
*@Date: Created in 2019/5/20 14:45
*@Modify By:
*/
public class AdvisedSupport {
    //要拦截的对象
    private TargetSource targetSource;
    //方法拦截器
    private MethodInterceptor methodInterceptor;
    //方法匹配器，判断是否是需要拦截的方法
    private MethodMatcher methodMatcher;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
