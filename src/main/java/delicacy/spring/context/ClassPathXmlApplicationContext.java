package delicacy.spring.context;

import delicacy.spring.beans.factory.AbstractBeanFactory;
import delicacy.spring.beans.factory.AutowireCapableBeanFactory;
import delicacy.spring.beans.xml.XmlBeanDefinitionReader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
*@Author: shaodilong
*@Description: 从类路径加载资源的IOC容器
*@Date: Created in 2019/5/8 11:14
*@Modify By:
*/
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {
    private String configLocation;
    public ClassPathXmlApplicationContext(String configLocation) throws ParserConfigurationException, SAXException, IOException {
        //默认自动装载bean
        this(configLocation,new AutowireCapableBeanFactory());
    }
    public ClassPathXmlApplicationContext(String configLocation, AbstractBeanFactory beanFactory) throws IOException, SAXException, ParserConfigurationException {
        super(beanFactory);
        this.configLocation=configLocation;

        refresh();
    }

    /**
     * 加载beanDefinition并注册
     *
     * @param beanFactory
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    @Override
    protected int loadBeanDefinitions(AbstractBeanFactory beanFactory) throws IOException, SAXException, ParserConfigurationException {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        // 从类路径加载xml文件中bean的定义并注册到AbstractBeanDefinitionReader的registry(beanFactory)中去
        return xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);
    }
}
