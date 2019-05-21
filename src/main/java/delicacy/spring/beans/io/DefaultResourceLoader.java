package delicacy.spring.beans.io;

import java.net.MalformedURLException;
import java.net.URL;

/**
*@Author: shaodilong
*@Description: 默认的资源加载器
*@Date: Created in 2019/5/8 14:51
*@Modify By:
*/
public class DefaultResourceLoader implements ResourceLoader{
    @Override
    public Resource getResource(String location) {
        if(location.startsWith("/")){
            return null;//ClassPathContextResource(...)
        }else if(location.startsWith("classpath:")){//类路径
            return null;//ClassPathResource(...)
        }else{
            try {
                //尝试解析location为URL
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                //尝试失败
                URL url = this.getClass().getClassLoader().getResource(location);
                return new UrlResource(url);//spring实际为ClassPathContextResource(...)
            }
        }
    }
}
