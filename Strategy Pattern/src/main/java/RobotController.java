public class RobotController {
    public static void main(String[] args) {
        Robot aggroRobot = new Robot("Aggro", new AggressiveBehaviour());
        aggroRobot.move();

        Robot defRobot = new Robot("Def", new DefensiveBehaviour());
        defRobot.move();
    }
}
