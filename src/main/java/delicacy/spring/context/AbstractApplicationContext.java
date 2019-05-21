package delicacy.spring.context;

import delicacy.spring.beans.BeanPostProcessor;
import delicacy.spring.beans.factory.AbstractBeanFactory;
import delicacy.spring.beans.io.DefaultResourceLoader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
*@Author: shaodilong
*@Description: ApplicationContext的抽象实现
*@Date: Created in 2019/5/8 10:55
*@Modify By:
*/
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ApplicationContext{
    protected AbstractBeanFactory beanFactory;
    public AbstractApplicationContext(AbstractBeanFactory beanFactory){
        this.beanFactory = beanFactory;
    }

    @Override
    public Object getBean(String name) throws InstantiationException, IllegalAccessException {
        return this.beanFactory.getBean(name);
    }
    //用于beanFactory的刷新，告诉BeanFactory使用什么Resource加载beanDefinition并实例初始化bean
    public void refresh() throws ParserConfigurationException, SAXException, IOException {
        //为BeanFactory加载beanDefinition
        loadBeanDefinitions(beanFactory);

        registerBeanPostProcessor(beanFactory);
        //实例化所有的bean
        onRefresh();
    }
    //由子类决定从哪种形式的Resource中加载出bean的定义，并加载到beanFactory中
    protected abstract int loadBeanDefinitions(AbstractBeanFactory beanFactory) throws IOException, SAXException, ParserConfigurationException;

    //默认以单例的方式实例化所有的bean
    protected void onRefresh(){
        beanFactory.preInstantiateSingletons();
    }

    /**
     * 可用于实例化AspectJAwareAdvisorAutoProxyCreator
     *
     * @param beanFactory
     */
    protected void registerBeanPostProcessor(AbstractBeanFactory beanFactory){
        try {
            List beanPostProcessors = beanFactory.getBeansForType(BeanPostProcessor.class);
            for(Object beanPostProcessor : beanPostProcessors){
                beanFactory.addBeanPostProcessor((BeanPostProcessor) beanPostProcessor);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
