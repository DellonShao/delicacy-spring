package delicacy.spring.aop;

import java.lang.reflect.Method;

/**
*@Author: shaodilong
*@Description: 方法匹配器
*@Date: Created in 2019/5/20 14:41
*@Modify By:
*/
public interface MethodMatcher {
    /**
     * 判断该方法是否是要匹配的方法
     *
     * @param method
     * @param targetClass
     * @return
     */
    boolean matches(Method method, Class targetClass);
}
