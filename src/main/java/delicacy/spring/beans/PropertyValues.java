package delicacy.spring.beans;

import java.util.ArrayList;
import java.util.List;

/**
*@Author: shaodilong
*@Description: 
*@Date: Created in 2019/5/7 16:21
*@Modify By:
*/
public class PropertyValues {
    private final List<PropertyValue> propertyValues = new ArrayList<PropertyValue>();

    public PropertyValues addPropertyValue(PropertyValue propertyValue){
        this.propertyValues.add(propertyValue);
        return this;
    }

    public PropertyValues() {
    }

    public List<PropertyValue> getPropertyValues(){
        return this.propertyValues;
    }
}
