package AGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AGameMain implements ActionListener{

    JButton button = new JButton("EXIT");
    public static void main(String[] args) throws InterruptedException {
        
        AGameMain agm = new AGameMain();
        //start
        agm.welcome();
        agm.Frame();
     
    }

    //sets JFrame and Panel where components will be placed
    public void Frame() throws InterruptedException {

        JFrame frame = new JFrame("Bang!");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(800,800);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.BLACK);

        JPanel space = new AGameGame();
        space.setBounds(0, 0, 800, 700); 
        space.setLayout(null);
        space.setBackground(Color.BLACK);
        frame.add(space);

        JLabel timer = new JLabel("30");
        timer.setForeground(Color.YELLOW);
        timer.setBounds(385, 700, 100, 20);
        frame.add(timer);

        frame.setVisible(true);
        
        int gametime = 45;
        //Starts the game timer, currently set to 30 seconds
        long startTime = System.currentTimeMillis();
        while(System.currentTimeMillis() < startTime + 45000) {
            Thread.sleep(1000);
            timer.setText(String.valueOf(gametime));
            gametime = gametime - 1;
             
        }
        
        //calls gameover, ends game
        gameover();

    }

    //Setting welcome window and its text
    public void welcome() {

        JFrame wframe = new JFrame("Welome to BANG!");
        wframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        wframe.setLayout(null);
        wframe.setSize(300, 300);
        wframe.setLocation(190, 200);
        wframe.getContentPane().setBackground(Color.BLACK);

        JLabel title = new JLabel("BANG!");
        title.setForeground(Color.YELLOW);
        title.setBounds(10, 10, 100, 20);
        wframe.add(title);

        JLabel ins = new JLabel("Instructions :");
        ins.setForeground(Color.YELLOW);
        ins.setBounds(10, 40, 100, 20);
        wframe.add(ins);

        JLabel stuff = new JLabel("Red = 5 Points");
        stuff.setForeground(Color.YELLOW);
        stuff.setBounds(10, 70, 150, 20);
        wframe.add(stuff);

        JLabel stuff1 = new JLabel("Orange = 4 Points");
        stuff1.setForeground(Color.YELLOW);
        stuff1.setBounds(10, 100, 150, 20);
        wframe.add(stuff1);

        JLabel stuff2 = new JLabel("Green = 3 Points");
        stuff2.setForeground(Color.YELLOW);
        stuff2.setBounds(10, 130, 150, 20);
        wframe.add(stuff2);

        JLabel stuff3 = new JLabel("Cyan = 2 Points");
        stuff3.setForeground(Color.YELLOW);
        stuff3.setBounds(10, 160, 150, 20);
        wframe.add(stuff3);

        JLabel war = new JLabel("Warning :");
        war.setForeground(Color.RED);
        war.setBounds(150, 40, 100, 20);
        wframe.add(war);

        JLabel war1 = new JLabel("45 Second");
        war1.setForeground(Color.RED);
        war1.setBounds(150, 70, 100, 20);
        wframe.add(war1);

        JLabel war2 = new JLabel("Time Limit");
        war2.setForeground(Color.RED);
        war2.setBounds(150, 100, 100, 20);
        wframe.add(war2);

        wframe.setVisible(true);

    }

    //opens the gameover window to officially close game
    public void gameover() {

        JFrame gframe = new JFrame("Game Over");
        gframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        gframe.setLayout(null);
        gframe.setSize(300, 300);
        gframe.setLocationRelativeTo(null);
        gframe.getContentPane().setBackground(Color.BLACK);

        //AGameGame agg = new AGameGame();

        JLabel bye = new JLabel("Console Score");
        bye.setForeground(Color.YELLOW);
        bye.setBounds(90, 70, 100, 50);
        gframe.add(bye);

        button.setBounds(90, 120, 100, 50);
        button.setForeground(Color.BLACK);
        button.setBackground(Color.YELLOW);
        gframe.add(button);

        button.addActionListener(this);

        gframe.setVisible(true);

    }

    //button action to close the game
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == button) {
        System.exit(0);

        }

    }

}

