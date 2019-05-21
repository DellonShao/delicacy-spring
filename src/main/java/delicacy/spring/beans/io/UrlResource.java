package delicacy.spring.beans.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
*@Author: shaodilong
*@Description: 这是一个通过url获取资源的类
*@Date: Created in 2019/5/6 21:22
*@Modify By:
*/
public class UrlResource implements Resource{
    private final URL url;//通过这个url获取资源
    public UrlResource(URL url){
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        return urlConnection.getInputStream();
    }
}
