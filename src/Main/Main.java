package Main;

import javax.swing.*;
import java.awt.*;

public class Main{

    public static JFrame window;

    public static void main(String[] args){

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        gamePanel.setUpGame();

        window.pack();
        window.setVisible(true);

        if(gamePanel.fullScreen){
            gamePanel.setFullScreen();
        }

        gamePanel.startThread();
    }
}