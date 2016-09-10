/**
 * To change the behaviour of the Robot, we don't have to change this class.
 * New behaviour can be implemented using Behaviour interface.
 * This is also an example of Open-Closed Principle. This class is open for extension but
 * closed for modification.
 */
public class Robot {
    private Behaviour behaviour;
    private String name;

    public Robot(String name, Behaviour behaviour) {
        this.behaviour = behaviour;
        this.name = name;
    }

    public int move() {
        System.out.print(name + " is ");
        return behaviour.move();
    }
}
