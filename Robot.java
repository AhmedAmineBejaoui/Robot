

public class Robot {

    private String nom;
    private int x, y;
    private DIRECTION direction;

    public Robot(String nom, int x, int y, DIRECTION direction) {
        this.nom = nom;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public Robot(String nom) {
        this.nom = nom;
        x = 0;
        y = 0;
        direction = DIRECTION.EST;
    }

    public void avancer() {
        switch (direction) {
            case EST:
                x++;
                break;
            case OUEST:
                x--;
                break;
            case NORD:
                y++;
                break;
            case SUD:
                y--;
                break;
            default:
                break;
        }
    }

    public void droit() {
        switch (direction) {
            case EST:
                direction = DIRECTION.SUD;
                break;
            case SUD:
                direction = DIRECTION.OUEST;
                break;
            case OUEST:
                direction = DIRECTION.NORD;
                break;
            case NORD:
                direction = DIRECTION.EST;
                break;
            default :break;
        }
    }

    public void affichage() {
        System.out.println("le nom du robot est : " + nom);
        System.out.println("la position du robot est : " + x + "," + y);
        System.out.println("la direction du robot est : " + direction.toString());

    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDirection(DIRECTION direction) {
        this.direction = direction;
    }

    public String getNom() {
        return nom;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public DIRECTION getDirection() {
        return direction;
    }
    
}
