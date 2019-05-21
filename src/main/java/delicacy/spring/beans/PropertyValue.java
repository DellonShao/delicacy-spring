package delicacy.spring.beans;
/**
*@Author: shaodilong
*@Description: 
*@Date: Created in 2019/5/7 16:22
*@Modify By:
*/
public class PropertyValue {
    private final String name;
    //对于xml中设置的键值对，值分为两种value以及ref，value类型可以直接注入，ref需要解析，再转化为对应的实例
    private final Object value;

    public PropertyValue(String name, Object value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
