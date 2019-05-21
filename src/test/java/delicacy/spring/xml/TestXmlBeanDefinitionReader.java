package delicacy.spring.xml;

import delicacy.spring.beans.BeanDefinitionReader;
import delicacy.spring.beans.factory.AbstractBeanFactory;
import delicacy.spring.beans.factory.AutowireCapableBeanFactory;
import delicacy.spring.beans.xml.XmlBeanDefinitionReader;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
*@Author: shaodilong
*@Description:
*@Date: Created in 2019/5/8 16:45
*@Modify By:
*/
public class TestXmlBeanDefinitionReader {
    @Test
    public void testXmlBeanDefinitionReader() throws IOException, SAXException, ParserConfigurationException {
        AbstractBeanFactory factory = new AbstractBeanFactory();
        BeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions("spring.xml");
        Assert.assertTrue(factory.getBeanDefinitionCount() > 0);
    }
}
