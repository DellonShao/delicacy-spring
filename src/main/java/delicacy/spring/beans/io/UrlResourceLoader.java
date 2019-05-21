package delicacy.spring.beans.io;

import java.net.URL;

/**
*@Author: shaodilong
*@Description: URL资源加载类
*@Date: Created in 2019/5/7 15:36
*@Modify By:
*/
public class UrlResourceLoader implements ResourceLoader {
    @Override
    public Resource getResource(String location) {
        URL url = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(url);
    }
}
