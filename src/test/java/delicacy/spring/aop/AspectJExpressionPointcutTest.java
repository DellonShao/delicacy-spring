package delicacy.spring.aop;

import delicacy.spring.Bird;
import delicacy.spring.Swallow;
import org.junit.Assert;
import org.junit.Test;

/**
*@Author: shaodilong
*@Description:
*@Date: Created in 2019/5/21 16:45
*@Modify By:
*/
public class AspectJExpressionPointcutTest {
    @Test
    public void testClassFilter() throws Exception {
        String expression = "execution(* delicacy.spring.Bird.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getClassFilter().matches(Bird.class);
        Assert.assertTrue(matches);
    }

    @Test
    public void testMethodInterceptor() throws Exception {
        String expression = "execution(* delicacy.spring.Swallow.fly(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getMethodMatcher()
                .matches(Swallow.class.getDeclaredMethod("sing"), Swallow.class);
        Assert.assertFalse(matches);
    }
}
