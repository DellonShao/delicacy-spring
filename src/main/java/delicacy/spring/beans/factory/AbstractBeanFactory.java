package delicacy.spring.beans.factory;

import delicacy.spring.beans.BeanDefinition;
import delicacy.spring.beans.BeanPostProcessor;
import delicacy.spring.beans.factory.support.BeanDefinitionRegistry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
*@Author: shaodilong
*@Description: BeanFactory 的一种抽象类实现，规范了 IoC 容器的基本结构，维护一个 beanDefinitionMap 哈希表用于保存类的定义信息（BeanDefinition）
*@Date: Created in 2019/5/7 21:14
*@Modify By:
*/
public class AbstractBeanFactory implements BeanFactory,BeanDefinitionRegistry{
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);
    /** List of bean definition names, in registration order. */
    private volatile List<String> beanDefinitionNames = new ArrayList<>(256);

    //bean的后置处理器
    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(beanName,beanDefinition);
        this.beanDefinitionNames.add(beanName);
    }

    @Override
    public void removeBeanDefinition(String beanName) {
        this.beanDefinitionMap.remove(beanName);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        BeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);
        return beanDefinition;
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return this.beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new String[0]);
    }

    @Override
    public int getBeanDefinitionCount() {
        return this.beanDefinitionMap.size();
    }

    @Override
    public boolean isBeanNameInUse(String beanName) {
        return containsBeanDefinition(beanName);
    }

    @Override
    public Object getBean(String name) throws IllegalAccessException, InstantiationException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if(beanDefinition == null){
            throw new IllegalArgumentException("No such bean: " + name);
        }
        Object bean = beanDefinition.getBean();
        //未装配bean
        if(bean == null){
            //装配bean并注入属性值
            bean = doCreateBean(beanDefinition);
            //初始化bean，例如生成相关代理类，用于AOP织入
            bean = initializeBean(bean,name);
            beanDefinition.setBean(bean);
        }
        return bean;
    }

    protected Object doCreateBean(BeanDefinition beanDefinition) throws InstantiationException, IllegalAccessException {
        Object bean = createBeanInstance(beanDefinition);
        beanDefinition.setBean(bean);
        // 注入属性的hook方法(参考模板方法设计模式中的hook方法)交给子类去实现
        applyPropertyValues(bean,beanDefinition);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition) throws IllegalAccessException, InstantiationException {
        return beanDefinition.getBeanClass().newInstance();
    }

    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws InstantiationException, IllegalAccessException {}

    /**
     * 初始化bean，可以在这个地方进行AOP的操作，如返回相关的代理类
     *
     * @param bean
     * @param beanName
     * @return
     */
    protected Object initializeBean(Object bean, String beanName) throws InstantiationException, IllegalAccessException {
        for(BeanPostProcessor beanPostProcessor : beanPostProcessors){
            bean = beanPostProcessor.postProcessBeforeInitialization(bean,beanName);
        }

        for(BeanPostProcessor beanPostProcessor : beanPostProcessors){
            bean = beanPostProcessor.postProcessAfterInitialization(bean, beanName);
        }
        return bean;
    }

    public void preInstantiateSingletons(){
        beanDefinitionNames.forEach(item-> {
            try {
                getBean(item);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 根据类型获取所有的bean实例
     * @param type
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public List getBeansForType(Class type) throws InstantiationException, IllegalAccessException {
        List beans = new ArrayList();
        for(String beanDefinitionName : beanDefinitionNames){
            //判定此 Class 对象所表示的类或接口与指定的 Class 参数所表示的类或接口是否相同，或是否是其超类或超接口。
            if(type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())){
                beans.add(getBean(beanDefinitionName));
            }
        }
        return beans;
    }

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        this.beanPostProcessors.add(beanPostProcessor);
    }
}

