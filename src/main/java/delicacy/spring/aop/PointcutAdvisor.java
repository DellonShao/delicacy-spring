package delicacy.spring.aop;
/**
*@Author: shaodilong
*@Description: 切点通知器
*@Date: Created in 2019/5/20 17:01
*@Modify By:
*/
public interface PointcutAdvisor extends Advisor{
    Pointcut getPointcut();
}
