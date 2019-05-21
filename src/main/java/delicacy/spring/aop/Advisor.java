package delicacy.spring.aop;

import org.aopalliance.aop.Advice;

/**
*@Author: shaodilong
*@Description: 通知器，用于实现具体的方法拦截，需要使用者编写，也就对应了 Spring 中的前置通知、后置通知、环切通知等。
*@Date: Created in 2019/5/20 14:06
*@Modify By:
*/
public interface Advisor {
    Advice getAdvice();
}