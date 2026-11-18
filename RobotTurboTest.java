/** Lightweight regression checks; run with: javac DIRECTION.java Robot.java RobotNG.java RobotTurboTest.java && java RobotTurboTest */
public class RobotTurboTest {
    private static void check(int expected, int actual, String label) {
        if (expected != actual) {
            throw new AssertionError(label + ": expected " + expected + ", got " + actual);
        }
    }

    public static void main(String[] args) {
        RobotNG robot = new RobotNG("test");
        robot.avancer();
        check(1, robot.getX(), "normal single step");

        robot.setTurbo(true);
        robot.avancer();
        check(4, robot.getX(), "turbo single step should move three units");

        robot.avancerNbrPas(2);
        check(10, robot.getX(), "turbo two steps should move six units");

        robot.setTurbo(false);
        robot.demiTour();
        robot.avancer();
        check(9, robot.getX(), "normal reverse step");
        System.out.println("RobotTurboTest passed");
    }
}
