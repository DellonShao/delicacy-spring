package delicacy.spring.beans;

import delicacy.spring.beans.factory.support.BeanDefinitionRegistry;
import delicacy.spring.beans.io.Resource;
import delicacy.spring.beans.io.ResourceLoader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
*@Author: shaodilong
*@Description: 用来解析BeanDefinition的接口
*@Date: Created in 2019/5/7 17:46
*@Modify By:
*/
public interface BeanDefinitionReader {
    //注册bean的定义
    BeanDefinitionRegistry getRegistry();
    //获取bean内容的资源加载器
    ResourceLoader getResourceLoader();
    int loadBeanDefinitions(Resource resource) throws IOException, ParserConfigurationException, SAXException;
    int loadBeanDefinitions(Resource... resources) throws ParserConfigurationException, SAXException, IOException;
    //根据地址加载bean定义
    int loadBeanDefinitions(String location) throws ParserConfigurationException, SAXException, IOException;
    int loadBeanDefinitions(String... locations) throws IOException, SAXException, ParserConfigurationException;
}
