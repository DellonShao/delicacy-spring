package delicacy.spring.beans.io;

/**
*@Author: shaodilong
*@Description: 资源加载接口
*@Date: Created in 2019/5/7 15:22
*@Modify By:
*/
public interface ResourceLoader {
    public Resource getResource(String location);
}
