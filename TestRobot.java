

public class TestRobot {

    public static void main(String[] args) {
        Robot[] robots = new Robot[100];
        robots[0] = new Robot("ahmed");
        robots[1] = new Robot("kamatchou", 100, 20, DIRECTION.SUD);
        robots[2] = new RobotNG("rabeb");
        robots[3] = new RobotNG("chirine", 3, 5, DIRECTION.NORD);
        ((RobotNG) robots[3]).setTurbo(false);

        //((RobotNG) robots[3]).avancerNbrPas(3);
       // ((RobotNG) robots[3]).demiTour();
               // ((RobotNG) robots[3]).droit();
                
                ((Robot)robots[1]).droit();
                ((Robot)robots[1]).affichage();


       // ((RobotNG) robots[3]).affichage();

    }

}
