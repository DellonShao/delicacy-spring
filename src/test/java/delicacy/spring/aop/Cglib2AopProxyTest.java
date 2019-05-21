package delicacy.spring.aop;

import delicacy.spring.Bird;
import delicacy.spring.Swallow;
import delicacy.spring.context.ApplicationContext;
import delicacy.spring.context.ClassPathXmlApplicationContext;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Test;

/**
*@Author: shaodilong
*@Description: 
*@Date: Created in 2019/5/21 16:49
*@Modify By:
*/
public class Cglib2AopProxyTest {
    @Test
    public void testInterceptor() throws Exception {
        // --------- helloWorldService without AOP
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Swallow swallow = (Swallow) applicationContext.getBean("swallow");
        swallow.sing();

        // --------- helloWorldService with AOP
        // 1. 设置被代理对象(JoinPoint)
        AdvisedSupport advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(swallow, Swallow.class, Bird.class);
        advisedSupport.setTargetSource(targetSource);

        // 2. 设置拦截器(Advice)
        TimerInterceptor timerInterceptor = new TimerInterceptor();
        advisedSupport.setMethodInterceptor(timerInterceptor);

        // 3. 设置方法匹配器
        String expression = "execution(* delicacy.spring.Swallow.sing(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        MethodMatcher methodMatcher = aspectJExpressionPointcut.getMethodMatcher();
        advisedSupport.setMethodMatcher(methodMatcher);

        // 4. 创建代理(Proxy)
        Cglib2AopProxy cglib2AopProxy = new Cglib2AopProxy(advisedSupport);
        Bird birdProxy = (Bird) cglib2AopProxy.getProxy();

        // 5. 基于AOP的调用
        birdProxy.sing();
        // 6. 方法未匹配的调用
        birdProxy.fly();
    }
}
