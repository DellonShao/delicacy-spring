package delicacy.spring.beans.io;

import java.io.IOException;
import java.io.InputStream;

/**
*@Author: shaodilong
*@Description: IOC容器获取bean配置的资源（xml）的地方
*@Date: Created in 2019/5/6 21:21
*@Modify By:
*/
public interface Resource {

    /**
     * 既然是获取资源，那么这个接口就应该有一个读取资源的地方
     * 获取资源的输入流
     *
     * @return
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;
}
