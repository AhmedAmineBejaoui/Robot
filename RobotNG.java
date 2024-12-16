
public class RobotNG extends Robot {

    private boolean turbo;

    public RobotNG(String nom, int x, int y, DIRECTION direction) {
        super(nom, x, y, direction);
        turbo = false;
    }

    public RobotNG(String nom) {
        super(nom);
        turbo = false;
    }

    public boolean hasTurbo() {
        return turbo;
    }

    public void setTurbo(boolean turbo) {
        this.turbo = turbo;
    }

    public void avancerNbrPas(int nbPas) {
        //for(int i=0;i<nbPas;i++)
        // {
        //    avancer();
        // }
        if (turbo) {
            nbPas *= 3;
        }
        switch (getDirection()) {
            case EST:
                setX(getX() + nbPas);
                break;
            case SUD:
                setY(getY() - nbPas);
                break;
            case OUEST:
                setX(getX() - nbPas);
                break;
            case NORD:
                setY(getY() + nbPas);
                break;
            default:
                break;
        }
    }

    @Override
    public void avancer() {
        if (turbo) {
            avancerNbrPas(3);
        } else {
            super.avancer();
        }
    }

    @Override
    public void affichage() {
        super.affichage();
        if (turbo) {
            System.out.println("turbo est active");
        } else {
            System.out.println("turbo est non active");

        }
    }

    public void gauche() {
        //  droit();
        // droit();
        //droit();

        switch (getDirection()) {
            case EST:
                setDirection(DIRECTION.NORD);
                break;
            case NORD:
                setDirection(DIRECTION.OUEST);
                break;
            case OUEST:
                setDirection(DIRECTION.SUD);
                break;
            case SUD:
                setDirection(DIRECTION.EST);

                break;
            default:
                break;
        }
    }

    public void demiTour() {
        // droit();
        // droit();
        switch (getDirection()) {
            case EST:
                setDirection(DIRECTION.OUEST);
                break;
            case NORD:
                setDirection(DIRECTION.SUD);
                break;
            case OUEST:
                setDirection(DIRECTION.EST);
                break;
            case SUD:
                setDirection(DIRECTION.NORD);

                break;
            default:
                break;
        }
    }

   

}
