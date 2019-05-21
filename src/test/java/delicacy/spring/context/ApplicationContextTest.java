package delicacy.spring.context;

import delicacy.spring.Bird;
import delicacy.spring.Wing;
import org.junit.Test;

/**
*@Author: shaodilong
*@Description:
*@Date: Created in 2019/5/8 16:30
*@Modify By:
*/
public class ApplicationContextTest {
    @Test
    public void testApplicationContext()throws Exception{
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Wing swallowSwing = (Wing)applicationContext.getBean("swallow_swing");
        swallowSwing.wave();
        Bird swallow = (Bird)applicationContext.getBean("swallow");
        swallow.sing();
        swallow.fly();
    }
}
