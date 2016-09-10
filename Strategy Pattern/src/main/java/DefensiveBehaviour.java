public class DefensiveBehaviour implements Behaviour{

    @Override
    public int move() {
        System.out.println("being defensive");
        return -1;
    }
}
