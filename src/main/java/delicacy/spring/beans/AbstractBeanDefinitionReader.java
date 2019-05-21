package delicacy.spring.beans;

import delicacy.spring.beans.factory.AbstractBeanFactory;
import delicacy.spring.beans.factory.support.BeanDefinitionRegistry;
import delicacy.spring.beans.factory.support.SimpleBeanDefinitionRegistry;
import delicacy.spring.beans.io.DefaultResourceLoader;
import delicacy.spring.beans.io.Resource;
import delicacy.spring.beans.io.ResourceLoader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;

/**
*@Author: shaodilong
*@Description: 
*@Date: Created in 2019/5/7 19:25
*@Modify By:
*/
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    private final BeanDefinitionRegistry registry;
    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry){
        this.registry = registry;
        this.resourceLoader = new DefaultResourceLoader();
    }
    @Override
    public BeanDefinitionRegistry getRegistry() {
        return this.registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }

    @Override
    public int loadBeanDefinitions(Resource resource) throws IOException, ParserConfigurationException, SAXException {
        return 0;
    }

    @Override
    public int loadBeanDefinitions(Resource... resources) throws ParserConfigurationException, SAXException, IOException {
        int count = 0;
        for(Resource resource : resources){
            count += loadBeanDefinitions(resource);
        }
        return count;
    }

    @Override
    public int loadBeanDefinitions(String location) throws ParserConfigurationException, SAXException, IOException {
        Resource resource = resourceLoader.getResource(location);
        int count = loadBeanDefinitions(resource);
        return count;
    }

    @Override
    public int loadBeanDefinitions(String... locations) throws IOException, SAXException, ParserConfigurationException {
        int count = 0;
        for(String location : locations){
            count += loadBeanDefinitions(location);
        }
        return count;
    }
}
