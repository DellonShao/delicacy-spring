package delicacy.spring;
/**
*@Author: shaodilong
*@Description:
*@Date: Created in 2019/5/8 16:09
*@Modify By:
*/
public class Swallow implements Bird{
    private Wing swallowWing;
    private String name;
    private int age;

    public Swallow(){
    }

    public Wing getSwallowWing() {
        return swallowWing;
    }

    public void setSwallowWing(Wing swallowWing) {
        this.swallowWing = swallowWing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void sing() {
        System.out.println("zzz~~~");
    }

    @Override
    public void fly() {
        System.out.println("Swallow is flying...");
    }
}
