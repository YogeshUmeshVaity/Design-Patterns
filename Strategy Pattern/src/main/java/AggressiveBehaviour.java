public class AggressiveBehaviour implements Behaviour {
    @Override
    public int move() {
        System.out.println("being aggressive");
        return 1;
    }
}
