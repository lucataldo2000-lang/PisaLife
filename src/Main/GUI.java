package Main;

import java.awt.*;

public class GUI {

    GamePanel gp;

    public GUI(GamePanel gp){
        this.gp = gp;
    }

    public void draw(Graphics2D g2){

        switch(gp.gameState){
            case 1 -> drawTitle(g2);
        }
    }

    public void drawTitle(Graphics2D g2){

        int x = gp.tileSize;
        int y = gp.tileSize * 2;

        g2.setColor(Color.BLACK);
        g2.fillRect(0,0, gp.screenWidth,gp.screenHeight);

        g2.setFont(new Font("Arial",Font.BOLD, 40));
        g2.setColor(Color.white);

        g2.drawString("GAME", x, y);

        g2.setFont(new Font("Arial",Font.BOLD, 30));

        y += 70;

        int ogY = y;

        String[] lines = {"New Game", "Continue", "Options", "Quit"};

        for(String line : lines){
            g2.drawString(line,x,y);
            y+= 50;
        }

        g2.drawString(">", x - 25, ogY + (50 * gp.handler.titleSelector));

    }

}
