import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RobotGUI {
    private JFrame frame;
    private JPanel panel;
    private Robot robot;
    private JButton avancerButton, droitButton, gaucheButton, turboButton;
    private Timer animationTimer;
    private int stepSize = 5; // Taille du pas de mouvement
    private ArrayList<Point> trajectoryPoints = new ArrayList<>();
    private Image robotImage;
    private Image backgroundImage; // Variable pour l'image de fond

    public RobotGUI() {
        frame = new JFrame("Robot Movement");
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBackground(g); // Dessiner l'image de fond
                drawTrajectory(g); // Dessiner la trajectoire
                drawRobot(g); // Dessiner le robot
            }
        };

        panel.setPreferredSize(new Dimension(500, 500));

        // Charger l'image de fond
        backgroundImage = new ImageIcon("background_image.jpg").getImage(); // Assurez-vous que "background_image.jpg" est dans le répertoire du projet

        // Charger une image pour le robot
        robotImage = new ImageIcon("robot_image.png").getImage(); // Assurez-vous que "robot_image.png" est dans le répertoire du projet

        robot = new RobotNG("Robbie", 200, 200, DIRECTION.EST);

        avancerButton = new JButton("Avancer");
        droitButton = new JButton("Tourner à Droite");
        gaucheButton = new JButton("Tourner à Gauche");
        turboButton = new JButton("Activer Turbo");

        setupButtonActions();

        JPanel controlPanel = new JPanel();
        controlPanel.add(avancerButton);
        controlPanel.add(droitButton);
        controlPanel.add(gaucheButton);
        controlPanel.add(turboButton);

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Timer pour une animation fluide
        animationTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.repaint();
            }
        });
        animationTimer.start();
    }

    private void setupButtonActions() {
        avancerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animateRobotMovement();
            }
        });

        droitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                robot.droit();
                panel.repaint();
            }
        });

        gaucheButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((RobotNG) robot).gauche();
                panel.repaint();
            }
        });

        turboButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleTurbo();
            }
        });
    }

    private void animateRobotMovement() {
        // Animation du robot
        new Thread(() -> {
            int targetX = robot.getX();
            int targetY = robot.getY();
            switch (robot.getDirection()) {
                case EST:
                    targetX += stepSize;
                    break;
                case OUEST:
                    targetX -= stepSize;
                    break;
                case NORD:
                    targetY -= stepSize;
                    break;
                case SUD:
                    targetY += stepSize;
                    break;
            }

            int deltaX = targetX - robot.getX();
            int deltaY = targetY - robot.getY();
            int steps = Math.max(Math.abs(deltaX), Math.abs(deltaY));

            for (int i = 0; i < steps; i++) {
                int newX = robot.getX() + (i * deltaX) / steps;
                int newY = robot.getY() + (i * deltaY) / steps;
                robot.setX(newX);
                robot.setY(newY);
                trajectoryPoints.add(new Point(newX, newY)); // Ajouter chaque position à la trajectoire
                try {
                    Thread.sleep(10); // Pause pour créer l'animation fluide
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            panel.repaint(); // Redessiner après l'animation
        }).start();
    }

    private void toggleTurbo() {
        RobotNG robotNG = (RobotNG) robot;
        boolean turboStatus = !robotNG.hasTurbo();
        robotNG.setTurbo(turboStatus);
        turboButton.setText(turboStatus ? "Désactiver Turbo" : "Activer Turbo");
        panel.repaint();
    }

    private void drawBackground(Graphics g) {
        // Dessiner l'image de fond
        g.drawImage(backgroundImage, 0, 0, panel.getWidth(), panel.getHeight(), null);
    }

    private void drawTrajectory(Graphics g) {
        // Dessiner la trajectoire du robot
        g.setColor(Color.GRAY);
        for (Point point : trajectoryPoints) {
            g.fillOval(point.x, point.y, 5, 5); // Chaque point de la trajectoire
        }
    }

    private void drawRobot(Graphics g) {
        // Dessiner le robot en utilisant une image
        g.drawImage(robotImage, robot.getX(), robot.getY(), 30, 30, null); // Dessiner l'image du robot
        g.setColor(Color.RED);
        g.drawString("Direction: " + robot.getDirection(), 20, 30);
        
        // Effet de turbo : Halo autour du robot si turbo est activé
        if (((RobotNG) robot).hasTurbo()) {
            g.setColor(Color.YELLOW);
            g.fillOval(robot.getX() - 10, robot.getY() - 10, 50, 50); // Halo autour du robot pour turbo
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RobotGUI();
            }
        });
    }
}
