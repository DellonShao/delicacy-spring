package delicacy.spring;

public class SwallowWing implements Wing{
    private final String color;

    public SwallowWing(){
        this.color = "BLACK";
    }

    @Override
    public void wave() {
        System.out.println("Swallow wave wings...");
    }
}
