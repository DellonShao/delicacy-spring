package delicacy.spring.aop;
/**
*@Author: shaodilong
*@Description: 切点，确定是对什么类的什么方法进行Aop
*@Date: Created in 2019/5/20 16:51
*@Modify By:
*/
public interface Pointcut {
    /**
     * 类名匹配，用于筛选要代理的目标对象
     *
     * @return
     */
    ClassFilter getClassFilter();

    /**
     * 方法名匹配
     *
     * @return
     */
    MethodMatcher getMethodMatcher();
}
