package delicacy.spring.beans.xml;

import delicacy.spring.BeanReference;
import delicacy.spring.beans.AbstractBeanDefinitionReader;
import delicacy.spring.beans.BeanDefinition;
import delicacy.spring.beans.PropertyValue;
import delicacy.spring.beans.factory.support.BeanDefinitionRegistry;
import delicacy.spring.beans.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

/**
*@Author: shaodilong
*@Description: 从xml文件中读取beanDefinition并注册
*@Date: Created in 2019/5/8 9:37
*@Modify By:
*/
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);

    }

    @Override
    public int loadBeanDefinitions(Resource resource) throws IOException, ParserConfigurationException, SAXException {
        //获取输入流
        InputStream inputStream = resource.getInputStream();
        //从xml中读取beanDefinition,并注册,此时beanDefinition中bean为设置
        int beanCount = doLoadBeanDefinitions(inputStream);
        inputStream.close();
        return beanCount;
    }

    protected int doLoadBeanDefinitions(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);
        //解析并注册其中的Bean
        return registerBeanDefinition(document);
    }

    public int registerBeanDefinition(Document document){
        //获取根标签<beans>
        Element root = document.getDocumentElement();
        return parseBeanDefinitions(root);
    }

    /**
     * 解析出所有的beanDefinition
     *
     * @param root
     * @return
     */
    public int parseBeanDefinitions(Element root){
        //获取所有的<bean>
        NodeList nodeList = root.getChildNodes();
        int count = 0;
        for(int i = 0; i < nodeList.getLength(); ++i){
            Node node = nodeList.item(i);
            if(node instanceof Element){
                Element element = (Element)node;
                //解析bean标签
                processBeanDefinition(element);
                ++count;
            }
        }
        return count;
    }

    /**
     * 解析bean标签
     *
     * @param element
     */
    public void processBeanDefinition(Element element){
        String beanName = element.getAttribute("id");
        String beanClassName = element.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        //设置beanClassName，同时内部设置beanClass
        beanDefinition.setBeanClassName(beanClassName);
        processProperty(element,beanDefinition);

        //注册beanDefinition
        super.getRegistry().registerBeanDefinition(beanName,beanDefinition);
    }

    /**
     * 解析property标签
     *
     * @param element
     * @param beanDefinition
     */
    public void processProperty(Element element, BeanDefinition beanDefinition){
        //获取该bean下所有的property
        NodeList propertyNodes = element.getElementsByTagName("property");
        for(int i = 0; i < propertyNodes.getLength(); ++i){
            Node propertyNode = propertyNodes.item(i);
            if(propertyNode instanceof Element){
                Element property = (Element)propertyNode;
                String propName = property.getAttribute("name");
                String propValue = property.getAttribute("value");
                if(propValue != null && propValue.length() > 0){
                    //处理value的类型
                    try {
                        Field field = beanDefinition.getBeanClass().getDeclaredField(propName);
                        Class cl = field.getType();
                        Object realValue = null;
                        if(cl.equals(int.class)){
                            realValue = (int)(Integer.parseInt(propValue));
                        }else if(cl.equals(double.class)){
                            realValue = (double)Double.parseDouble(propValue);
                        }else if(cl.equals(boolean.class)){
                            realValue = (boolean)Boolean.parseBoolean(propValue);
                        }else if(cl.equals(byte.class)){
                            realValue = (byte)Byte.parseByte(propValue);
                        }else if(cl.equals(float.class)){
                            realValue = (float)Float.parseFloat(propValue);
                        }else if(cl.equals(String.class)){
                            realValue = propValue;
                        }else{
                            throw new IllegalArgumentException("Unrecognized value");
                        }
                        beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(propName,realValue));
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }

                }else{//简单判断value是否是ref
                    String ref = property.getAttribute("ref");
                    if(ref == null || ref.length() == 0){
                        throw new IllegalArgumentException("Configuration problem: <property> element for property " + propName + " must specify a ref or value");
                    }
                    BeanReference beanReference = new BeanReference(ref);
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(propName,beanReference));
                }
            }
        }
    }
}
