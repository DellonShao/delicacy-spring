package delicacy.spring.io;

import delicacy.spring.beans.io.DefaultResourceLoader;
import delicacy.spring.beans.io.Resource;
import delicacy.spring.beans.io.ResourceLoader;
import delicacy.spring.beans.io.UrlResourceLoader;
import org.junit.Assert;
import org.junit.Test;

/**
*@Author: shaodilong
*@Description:
*@Date: Created in 2019/5/8 16:37
*@Modify By:
*/
public class ResourceLoaderTest {
    @Test
    public void testUrlResourceLoader(){
        ResourceLoader resourceLoader = new UrlResourceLoader();
        Resource resource = resourceLoader.getResource("spring.xml");
        Assert.assertNotNull(resource);
    }
    @Test
    public void testDefaultResourceLoader(){
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("spring.xml");
        Assert.assertNotNull(resource);
    }
}
