package delicacy.spring.aop;

import org.aopalliance.aop.Advice;

/**
*@Author: shaodilong
*@Description: AspectJ表达式切点通知器
*@Date: Created in 2019/5/20 17:36
*@Modify By:
*/
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor{
    //AspectJ表达式切点匹配器
    //AspectJ表达式匹配的切点，默认有初始化，也默认有了MethodMatcher
    private AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    //方法拦截器，这个需要用户自己去xml文件里配置方法拦截器（MethodInterceptor继承Advice接口），
    //在AspectJAwareAdvisorAutoProxyCreator设置代理对象的方法拦截器时将Advisor强转为MethodInterceptor
    private Advice advice;

    public void setAdvice(Advice advice){
        this.advice = advice;
    }

    public void setExpression(String expression){
        this.pointcut.setExpression(expression);
    }


    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }
}
