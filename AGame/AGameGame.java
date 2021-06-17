package AGame;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;

public class AGameGame extends JPanel implements ActionListener, KeyListener{

    //Arrays go from top to bottom
    int xwave[] = {20, 650, 20, 755, 30};
    int ywave[] = {20, 100, 180, 280, 450};

    //ArrayList to store player score and publish
    ArrayList<Integer> score = new ArrayList<>();
    
    int scores;

    int cyanstat = 2;
    int greenstat = 3;
    int orangestat = 4;
    int redstat = 5;

    Timer timer = new Timer(5, this);

    int x = 390;
    int y = 650;
    int Velx = 0;

    boolean readyToFire = false;
    boolean shot = false;

    Rectangle player;

    Rectangle enemy1;
    Rectangle enemy2;
    Rectangle enemy3;
    Rectangle enemy4;

    Rectangle miss;

    Rectangle bullet;

    int bx;
    int by;

    int xVelocity = 10;
    int xVelocity1 = 4;
    int xVelocity3 = 2;
    int xVelocity4 = 4;

    //start

    public AGameGame() {

        timer.start();

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

    }

    //Drawing, setting coordinates and sizes for components

    public void paint(Graphics g) {

        super.paint(g);

        //call shoot method to enable player to shoot
        shoot();

        player = new Rectangle(x, y, 10, 15);
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 10, 15);

        enemy1 = new Rectangle(xwave[0], ywave[0], 10, 10);
        g.setColor(Color.RED);
        g.fillRect(xwave[0], ywave[0], 10, 10);

        enemy2 = new Rectangle(xwave[1], ywave[1], 30, 30);
        g.setColor(Color.ORANGE);
        g.fillRect(xwave[1], ywave[1], 30, 30);

        enemy3 = new Rectangle(xwave[3], ywave[3], 50, 50);
        g.setColor(Color.green);
        g.fillRect(xwave[3], ywave[3], 50, 50);

        enemy4 = new Rectangle(xwave[4], ywave[4], 70, 70);
        g.setColor(Color.CYAN);
        g.fillRect(xwave[4], ywave[4], 70, 70);

        miss = new Rectangle(0, 0, 800, 10);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 10);

        //Drawing the bullet if demands are met

        if (shot) {

            g.setColor(Color.RED);
            g.fillRect(bullet.x + 4, bullet.y, 3, 5);

            //checks if the bullet hits a target or misses
            bulletcollision();
        
        }  


    }

    @Override
    public void keyTyped(KeyEvent e) {}

    //Checks when a specific key is pressed
    @Override
    public void keyPressed(KeyEvent e) {
        
    //Gets the key input
    int key = e.getKeyCode();

    //Player Movement LEFT, RIGHT
    if (key == KeyEvent.VK_LEFT) {
        Velx = -3;
    }
    if (key == KeyEvent.VK_RIGHT) {
        Velx = 3;
    }

    //shoot function

    if (key == KeyEvent.VK_UP) {

        if (bullet == null) 
            readyToFire = true;
        
        if (readyToFire) {
            bx = x;
            by = y;
            
            bullet = new Rectangle(bx, by, 3, 5);
            shot = true;

        }

    }
        
    }   

    //Checks when a key is released
    @Override
    public void keyReleased(KeyEvent e) {
    
        int keyCode = e.getKeyCode();

        if (keyCode == e.VK_RIGHT) {
            Velx = 0;
        }
        if (keyCode == e.VK_LEFT) {
            Velx = 0;
        }

        if (keyCode == e.VK_UP) {

        readyToFire = false;

        if(bullet.y <= 0) {

                removebullet();
                
        }
        }
        
    }

    //Handles the movement animation of all components
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //setting bounds for the player
        if (x < 0) {
            Velx = 0;
            x = 0;
        }
        if (x > 770) {
            Velx = 0;
            x = 770;
        }

        //formula for player movement
        x = x + Velx;

        //formula for enemy movement
        xwave[0] = xwave[0] + xVelocity;
        xwave[1] = xwave[1] + xVelocity1;
        xwave[3] = xwave[3] + xVelocity3;
        xwave[4] = xwave[4] + xVelocity4;

        //setting the boundaries
        //ledge == left edge, redge == right edge
        int ledge = 0;
        int redge = 745;

        //enemy 1
        if (xwave[0] >= redge) {
           xVelocity = -9;
        }
        if (xwave[0] <= ledge) {
            xVelocity = 9;
        }

        //enemy 2
        if (xwave[1] >= redge) {
            xVelocity1 = -3;
        }
        if (xwave[1] <= ledge) {
            xVelocity1 = 3;
        }

        //enemy 3
        if (xwave[3] >= redge) {
            xVelocity3 = -7;
        }
        if (xwave[3] <= ledge) {
            xVelocity3 = 7;
        }
        
        //enemy 4
        if (xwave[4] >= 720) {
            xVelocity4 = -4;
        }
        if (xwave[4] <= ledge) {
            xVelocity4 = 4;
        }

        //repaints the components to show fluid movement
        repaint();
        
   }

    //determines how fast the bullet travels (currently 8 pixels fast)
    public void shoot() {
    
        if (shot)

            bullet.y = bullet.y-- - 8;

    }

    //conditions for when the bullet hits or misses targets
    public void bulletcollision(){
        //enemy1
        if(bullet.intersects(enemy1)) {
            score.add(redstat);
            System.out.println("RED HIT");
            scorecounter();
            removebullet();
        }
        //enemy2
        if(bullet.intersects(enemy2)) {
            score.add(orangestat);
            System.out.println("ORANGE HIT");
            scorecounter();
            removebullet();
        }
        //enemy3
        if(bullet.intersects(enemy3)) {
            score.add(greenstat);
            System.out.println("GREEN HIT");
            scorecounter();
            removebullet();
        }
        //enemy4
        if(bullet.intersects(enemy4)) {
            score.add(cyanstat);
            System.out.println("CYAN HIT");
            scorecounter();
            removebullet();
        }
        //miss
        if(bullet.intersects(miss)) {
            System.out.println("MISSED");
            scorecounter();
            removebullet();
        }

    }
    
    //Deletes bullet when it collides with another component, prepares the bullet for the next shot
    public void removebullet() {
        bullet = new Rectangle(0, 0, 0 ,0);
        shot = false;
        readyToFire = true;

    }
    
    //constantly adds score to an array to be reviewed by the player
    public void scorecounter() {

        scores = score.stream().mapToInt(Integer::intValue).sum();
        System.out.println("ARRAY = " +score);
        System.out.println("TOTAL = " +scores);

    }

       
}

