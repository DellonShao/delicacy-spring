package delicacy.spring.aop;
/**
*@Author: shaodilong
*@Description: 类匹配器
*@Date: Created in 2019/5/20 16:53
*@Modify By:
*/
public interface ClassFilter {
    /**
     * 用于匹配targetClass是否是要拦截的类
     *
     * @param targetClass
     * @return
     */
    boolean matches(Class targetClass);
}
