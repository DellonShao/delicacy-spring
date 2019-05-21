package delicacy.spring.aop;
/**
*@Author: shaodilong
*@Description: 暴露获取aop代理对象方法的接口
*@Date: Created in 2019/5/20 14:14
*@Modify By:
*/
public interface AopProxy {
    /**
     * 获取代理对象
     *
     * @return
     */
    Object getProxy();
}
